package com.alipay.antbenchmark.controller.ScannerStat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.antbenchmark.tools.SystemConsts;
import com.alipay.antbenchmark.tools.YamlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static com.alipay.antbenchmark.tools.Utils.checkFastJsonExploit;

/**
 * @date: 2023/4/23
 */
@Controller
@RequestMapping("/api")
public class StatApiController {

    private static final Logger log = LoggerFactory.getLogger("StatapiController");

    private static JSONObject initScanResult = null;

    public static HashMap<String, String[]> selfVulns = null;

    /**
     * 当前的扫描ID（scanid)
     */
    private int SCANID = -1;

    /**
     * 执行sql
     */
    public ResultSet execSql(String sql, String[] params) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(SystemConsts.JDBC_URL, SystemConsts.JDBC_NAME, SystemConsts.JDBC_PASSWORD);
            //connection.setAutoCommit(true);
            java.sql.PreparedStatement statement = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    statement.setString(i + 1, params[i]);
                }
            }
            ResultSet rs = null;
            //select型的SQL
            if (Character.toLowerCase(sql.charAt(0)) == 's') {
                rs = statement.executeQuery();
                log.info("execsql . execute select");
            } else {
                statement.executeUpdate();
                log.info("execsql . execute update");
            }
            return rs;
        } catch (Exception e) {
            log.info("execsql . found exceptin: {} ", e.getMessage());
            return null;
        }
    }


    /**
     * 获取靶场自身已有的漏洞信息
     * 是个HASHMAP,KEY是漏洞的参数名（字符串），VALUE是个数组，第一项是漏洞是否真实存在，第二项是漏洞类型
     */
    public HashMap<String, String[]> getOriginVulnInfo() {
        if (selfVulns != null) {
            return selfVulns;
        }
        selfVulns = new HashMap<String, String[]>();
        log.info("getOriginVulnInfo . initializing selfvulns..");
        try {
            URL url = this.getClass().getResource("/scorecard/").toURI().toURL();
            log.info("getOriginVulnInfo .the url:{}", url);
            //jar内部
            if (SystemConsts.PROTOCOL_JAR.equals(url.getProtocol())) {
                log.info("getOriginVulnInfo . Inside the Jar! the url:{}", url);
                String path = url.getPath();
                String jarPath = path.substring(5, path.indexOf("!"));
                try (JarFile jar = new JarFile(URLDecoder.decode(jarPath, StandardCharsets.UTF_8.name()))) {
                    log.info("getOriginVulnInfo .  the jarPath:{}", jarPath);
                    Enumeration<JarEntry> entries = jar.entries();
                    //遍历资源
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        String name = entry.getName();
                        // 非XML
                        URL resourceurl = Thread.currentThread().getContextClassLoader().getResource(name);
                        log.info("getOriginVulnInfo no yaml . Inside the Jar! the resourceurl:{} ,the name:{}", resourceurl, name);
                        if (!resourceurl.toString().endsWith(".yaml") || !resourceurl.toString().contains("/scorecard/")) {
                            continue;
                        }
                        log.info("getOriginVulnInfo is yaml . Inside the Jar! the resourceurl:{} ,the name:{}", resourceurl, name);
                        // 处理yaml文件值
                        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        log.info("getOriginVulnInfo deal. Inside the Jar! the resourceurl:{} ,the name:{}", resourceurl, name);
                        String category = null;
                        String vulnexist = null;
                        String testnumber = null;
                        String s = "";
                        while ((s = br.readLine()) != null) {
                            s = s.replaceAll(" ", "");
                            if (s.contains(SystemConsts.YAML_KEY_CATEGORY)) {
                                category = getYarmValue(s);
                            }
                            if (s.contains(SystemConsts.YAML_KEY_VULNERABILITY)) {
                                vulnexist = getYarmValue(s);
                            }
                            if (s.contains(SystemConsts.YAML_KEY_TEST_NUMBER)) {
                                testnumber = getYarmValue(s);
                            }
                        }
                        testnumber = "BS" + testnumber;
                        String[] values = new String[]{vulnexist, category};
                        log.info("get yaml value. the category:{} , the vulnexist:{} , the testnumber:{} ", category, vulnexist, testnumber);
                        selfVulns.put(testnumber, values);
                    }
                }
            } else {
                log.info("getOriginVulnInfo . In IDE");
                String path = (new File("")).getAbsolutePath() + "/src/main/resources/scorecard/";
                File f = new File(path);
                String[] pathnames = f.list();
                for (String pathname : pathnames) {
                    String fileName = path + pathname;
                    Map<String, String> properties = YamlReader.getYamlValue(fileName);
                    if (properties.isEmpty()) {
                        throw new IllegalStateException("getYamlValue is null ! please check !");
                    }
                    String category = properties.get("category");
                    String vulnexist = properties.get("vulnerability");
                    String testnumber = "BS" + properties.get("test-number");
                    String[] values = new String[]{vulnexist, category};
                    selfVulns.put(testnumber, values);
                }
            }
            log.info("selfvulns initializing success");
            return selfVulns;
        } catch (Exception e) {
            log.error("getOriginVulnInfo error. the exception:{}", e.getMessage());
            return selfVulns;
        }
    }

    public String getYarmValue(String text) {
        return text.substring(text.indexOf('\"') + 1, text.length() - 1);
    }

    /**
     * 初始状态时的漏洞扫描情况
     */
    public JSONObject getInitStatusScanResult() {
        if (initScanResult != null) {
            return (JSONObject) initScanResult.clone();
        }
        log.info("initializating the initial vuln state...");
        initScanResult = new JSONObject();
        initScanResult.put("vulns", new JSONObject());
        initScanResult.put("uncategoryvulns", new JSONArray());
        HashMap<String, String[]> vulns = getOriginVulnInfo();
        for (Map.Entry<String, String[]> entry : vulns.entrySet()) {
            String vulntype = entry.getValue()[1];
            String vulnexists = entry.getValue()[0];
            String paramname = entry.getKey();
            try {
                //如果这个类型，不在scanresult里面，添加一个新的
                if (!initScanResult.getJSONObject("vulns").containsKey(vulntype)) {
                    JSONObject obj = JSONObject.parseObject("{\"TP\":0,\"FP\":0,\"TN\":0,\"FN\":0,\"FPVULNS\":[],\"FNVULNS\":[]}");
                    initScanResult.getJSONObject("vulns").put(vulntype, obj);
                }
                //当前假设扫描器没有扫描到任何一个漏洞
                //没有扫描到任何漏洞的情况下
                //对于每一个漏洞，如果漏洞真实存在，就是False Negative，否则是True Negative
                String addedtype = vulnexists.equals("true") ? "FN" : "TN";
                initScanResult.getJSONObject("vulns").getJSONObject(vulntype).put(addedtype, initScanResult.getJSONObject("vulns").getJSONObject(vulntype).getInteger(addedtype) + 1);
                //对于False Negative要记录滴
                if (vulnexists.equals("true")) {
                    initScanResult.getJSONObject("vulns").getJSONObject(vulntype).getJSONArray("FNVULNS").add(paramname);
                }
            } catch (Exception e) {
                log.error("getInitStatusScanResult error ! the exception:{}", e.getMessage());
            }
        }
        JSONObject jsonObject = (JSONObject) initScanResult.clone();

        log.info("getInitStatusScanResult . the data json:{}", jsonObject);
        return jsonObject;
    }

    /**
     * 获取当前的scanid
     */
    private int getCurrentScanId() throws SQLException {
        if (SCANID == -1) {
            ResultSet rs = execSql("select scanid from scans order by time desc limit 1;", null);
            rs.next();
            SCANID = rs.getInt("scanid");
            if (rs != null) {
                rs.close();
            }
        }
        return SCANID;
    }

    /**
     * 刷新当前的scanid
     */
    public void refreshScanId() throws SQLException {
        SCANID = -1;
        getCurrentScanId();
    }


    /**
     * 创建一个新的统计记录
     */
    @ResponseBody
    @RequestMapping(value = "/startnewstat", method = RequestMethod.POST)
    public String startNewStat(HttpServletRequest request) throws SQLException {
        if (request.getParameter("start").equals("true")) {
            //插入新的扫描
            ResultSet rs = execSql("INSERT INTO `scans` (time,scanresultjson) VALUES(NOW(),?);", new String[]{getInitStatusScanResult().toString()});
            //刷新当前的scanid
            refreshScanId();
            //返回scanid
            return String.valueOf(getCurrentScanId());
        }
        return "no start param!";
    }

    @ResponseBody
    @RequestMapping(value = "/scancallback", method = RequestMethod.POST)
    public String scanCallback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        try {
            //获取JSON
            BufferedReader streamreader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamreader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            if (checkFastJsonExploit(responseStrBuilder.toString())) {
                return "don't hack me";
            }
            log.info("call back . the value:{}", responseStrBuilder.toString());
            JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());

            //提交的漏洞列表
            JSONArray vulns = jsonObject.getJSONArray("vuls");
            //如果vulns的长度大于0，即存在漏洞
            String vulnexists = "1";
            if (vulns.size() == 0)
                vulnexists = "0";
            //benchmark编号
            String benchmarknumber = jsonObject.getString("url");
            benchmarknumber = "BS" + benchmarknumber.split("/BS")[1].split("\\?")[0];
            //当前的scanid
            String scanid = request.getParameter("scanid");

            //提取漏洞类型
            String vulntype = "";
            if (vulnexists.equals("1")) {
                JSONObject vuln = vulns.getJSONObject(0);
                if (vuln.containsKey("vul_type"))
                    vulntype = vuln.getString("vul_type");
            }
            vulntype = vulntype.toLowerCase();
            //数据库来防重
            //ALTER TABLE scancallbacklog ADD UNIQUE KEY(vulnparam, scanid);
            String[] params = new String[]{scanid, benchmarknumber, vulntype, vulnexists};
            ResultSet rs = execSql("INSERT INTO `scancallbacklog` (`scanid`, `vulnparam`, `vulntype`,`vulnexists`, `time`) VALUES (?, ?, ?,?, NOW()) ", params);
            if (rs != null) {
                rs.close();
            }
            return "OK";
        } catch (Exception e) {
            log.error("scanCallback error . the exception:{}", e.getMessage());
            return "False";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/fetchcallbackcount", method = RequestMethod.GET)
    public String fetchCallbackCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int scanId = getCurrentScanId();
        ResultSet rs = execSql("select count(*) AS COUNT  from scancallbacklog where scanid=?", new String[]{new Integer(scanId).toString()});
        rs.next();
        String data = new Integer(rs.getInt("COUNT")).toString();
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/fetchresult", method = RequestMethod.GET)
    public String fetchResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        try {
            //靶场预设的漏洞
            HashMap<String, String[]> presetvulns = getOriginVulnInfo();

            //获取scanid
            int scanid = -1;
            if (request.getParameter("scanid") == null) {
                scanid = getCurrentScanId();
            } else
                scanid = Integer.parseInt(request.getParameter("scanid"));

            //获取扫描一开始的时候的JSON
            ResultSet rs = execSql("select  scanresultjson  from scans where scanid=? ", new String[]{Integer.toString(scanid)});
            rs.next();
            JSONObject scanresultjson = JSONObject.parseObject(rs.getString("scanresultjson"));
            JSONObject vulnjson = scanresultjson.getJSONObject("vulns");
            //获取已经提交过的认为存在的所有漏洞
            rs = execSql("select vulnparam, vulntype from scancallbacklog where scanid=? and vulnexists", new String[]{Integer.toString(scanid)});
            while (rs.next()) {
                // 扫描器扫描的接口编号
                String benchmarknumber = rs.getString("vulnparam");
                // vulntype 该编号对应的正确漏洞类型
                String vulntype = presetvulns.get(benchmarknumber)[1];

                if (vulnjson.getJSONObject(vulntype).getJSONArray("TPVULNS") == null) {
                    vulnjson.getJSONObject(vulntype).put("TPVULNS", new JSONArray());
                }
                // TP：对某个漏洞类型的接口，识别为该漏洞类型
                // 检测类型和实际漏洞类型不匹配，不进行计数，避免一个接口检测出多种漏洞的情况
                if (rs.getString("vulntype") != null && !vulntype.equals(rs.getString("vulntype"))) {
                    continue;
                }

                //类型参数一样 漏洞真实：TP++;FN--;
                if (presetvulns.get(benchmarknumber)[0].equals("true")   // 漏洞为真实漏洞
                        && vulntype.equals(rs.getString("vulntype")  // 当前接口被发现的漏洞类型和靶场本身漏洞类型一致
                )) {
                    //TP++
                    vulnjson.getJSONObject(vulntype).put("TP", vulnjson.getJSONObject(vulntype).getInteger("TP") + 1);
                    //FN--
                    vulnjson.getJSONObject(vulntype).put("FN", vulnjson.getJSONObject(vulntype).getInteger("FN") - 1);
                    //FNVULNS删当前漏洞
                    vulnjson.getJSONObject(vulntype).getJSONArray("FNVULNS").remove(benchmarknumber);
                    vulnjson.getJSONObject(vulntype).getJSONArray("TPVULNS").add(benchmarknumber);
                }
                //类型参数一样，但是实际上是误报:FP++;TN--;
                else {
                    //FP++;
                    vulnjson.getJSONObject(vulntype).put("FP", vulnjson.getJSONObject(vulntype).getInteger("FP") + 1);
                    //FN--
                    vulnjson.getJSONObject(vulntype).put("TN", vulnjson.getJSONObject(vulntype).getInteger("TN") - 1);
                    //FPVULNS增加当前漏洞
                    vulnjson.getJSONObject(vulntype).getJSONArray("FPVULNS").add(benchmarknumber);
                }

            }
            if (rs != null) {
                rs.close();
            }
            return scanresultjson.toString();
        } catch (Exception e) {
            log.error("exception:{}", e.getMessage());
            return "error!";
        }
    }
}
