package com.iast.astbenchmark.cases.engine.taint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iast.astbenchmark.analyser.cache.IastTestCase;
import com.iast.astbenchmark.common.CommonConsts;
import com.iast.astbenchmark.common.utils.TaintMethodUtil;

/**
 * 基础跟踪能力->污点链路完整度（case0022～case00112）
 */
@RestController()
public class AstTaintCase002 {

    /** 污点来源识别能力 */

    /// **
    // * 污点来自http url getContenPath
    // *
    // * @param
    // * @return
    // */
    // @PostMapping("case0028")
    // @Deprecated
    // public Map<String, Object> aTaintCase0028(HttpServletRequest request) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String datas = request.getContextPath();
    // Runtime.getRuntime().exec(datas);
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // }
    // return modelMap;
    // }
    //
    /// **
    // * 污点来自http url getRequestURL
    // *
    // * @param
    // * @return
    // */
    // @PostMapping("case0029")
    // @Deprecated
    // public Map<String, Object> aTaintCase0029(HttpServletRequest request) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String datas = String.valueOf(request.getRequestURL());
    // Runtime.getRuntime().exec(datas);
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // }
    // return modelMap;
    // }
    //
    /// **
    // * 污点来自http url getServletPath
    // *
    // * @param
    // * @return
    // */
    // @PostMapping("case0030")
    // @Deprecated
    // public Map<String, Object> aTaintCase0030(HttpServletRequest request) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String datas = request.getServletPath();
    // Runtime.getRuntime().exec(datas);
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // }
    // return modelMap;
    // }
    //
    /// **
    // * 污点来自http url getRequestURI
    // *
    // * @param
    // * @return
    // */
    // @PostMapping("case0031")
    // @Deprecated
    // public Map<String, Object> aTaintCase0031(HttpServletRequest request) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String datas = request.getRequestURI();
    // Runtime.getRuntime().exec(datas);
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // }
    // return modelMap;
    // }

    /// **
    // * 污点来自http url getPathInfo
    // *
    // * @param
    // * @return
    // */
    // @PostMapping("case0032")
    // @Deprecated
    // public Map<String, Object> aTaintCase0032(HttpServletRequest request) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String datas = request.getPathInfo();
    // Runtime.getRuntime().exec(datas);
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // }
    // return modelMap;
    // }

    // /**
    // * 污点来自http pathVarlables参数值数字
    // * @param cmd ls
    // * @return
    // */
    // @PostMapping(value = "case0042/{cmd}")
    // public Map<String, Object> aTaintCase0042(@PathVariable String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // Runtime.getRuntime().exec(cmd);
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }
    //
    // /**
    // * 污点来自http pathVarlables参数值字符串
    // * @param cmd aa
    // * @return
    // */
    // @PostMapping(value = "case0043/{cmd}")
    // public Map<String, Object> aTaintCase0043(@PathVariable String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // Runtime.getRuntime().exec(cmd);
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }

    /** 污点传播跟踪能力 */

    // /**
    // * 污点来自固定参数
    // *
    // * @param
    // * @return
    // */
    // @PostMapping(value = "case0048")
    // public Map<String, Object> aTaintCase0048(@RequestParam(defaultValue = "ls") String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String exec = String.valueOf(cmd);
    // Runtime.getRuntime().exec(exec);
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }
    //
    // /**
    // * 污点来自可变参数
    // * @param
    // * @return
    // */
    // @PostMapping(value = "case0049")
    // public Map<String, Object> aTaintCase0049(@RequestParam String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // Runtime.getRuntime().exec(String.format("%s -la",cmd));
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }
    //
    //
    // /**
    // * 污点来自对象实例
    // *
    // * @param
    // * @return
    // */
    // @PostMapping(value = "case0050")
    // public Map<String, Object> aTaintCase0050(@RequestBody SourceTestObject source ) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String exec = source.getCmd();
    // Runtime.getRuntime().exec(exec);
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }
    //
    // /**
    // *
    // * 污点来自多个参数
    // * @param
    // * @return
    // */
    // @PostMapping(value = "case0051")
    // public Map<String, Object> aTaintCase0051(@RequestParam String cmd1,@RequestParam String cmd2 ) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // Runtime.getRuntime().exec( String.format("%s -%s",cmd1,cmd2));
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }
    //
    // /**
    // * 污点传播目标为某个固定参数
    // * TODO Map 这里用的 listcpoy 再找下
    // * @param
    // * @return
    // */
    // @PostMapping(value = "case0052")
    // public Map<String, Object> aTaintCase0052(@RequestParam(defaultValue = "ls") String cmd ) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String strs[] = new String[1];
    // strs[0]=cmd;
    // List<String> target = Lists.newArrayList("cd /","ls","ls -la");
    // CollectionUtils.mergeArrayIntoCollection(strs,target);
    // Runtime.getRuntime().exec(target.get(3));
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }
    //
    // /**
    // * 污点传播目标为方法返回值
    // *
    // * @param
    // * @return
    // */
    // @PostMapping(value = "case0053")
    // public Map<String, Object> aTaintCase0053(@RequestParam(defaultValue = "ls") String cmd ) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String exec = String.format("%s -la",cmd);
    // Runtime.getRuntime().exec(exec);
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }
    //
    // /**
    // * 污点传播目标为对象实例
    // * @param
    // * @return
    // */
    // @PostMapping(value = "case0054")
    // public Map<String, Object> aTaintCase0054(@RequestBody SourceTestWithConstract01Bean bean) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // SourceTestWithConstract01Bean bean1 = MyCommonTestUtil.buildTestObject("cd ~");
    // SourceTestWithConstract01Bean bean2 = MyCommonTestUtil.buildTestObject("cd /");
    // SourceTestWithConstract01Bean[] beans = new SourceTestWithConstract01Bean[1];
    // beans[0]=bean;
    // List<SourceTestWithConstract01Bean> target = Lists.newArrayList(bean1,bean2);
    // CollectionUtils.mergeArrayIntoCollection(beans,target);
    // Runtime.getRuntime().exec(target.get(2).getCmd());
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }
    //
    // /**
    // * 污点传播目标为多个参数
    // * //TODO 一个污点传播到多个target
    // * @param
    // * @return
    // */
    // @PostMapping(value = "case0055")
    // public Map<String, Object> aTaintCase0055(@RequestParam(defaultValue = "ls") String
    // cmd1,@RequestParam(defaultValue = "ls")
    // String cmd2 ) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // //Map<String,String> map = MyCommonTestUtil.buildTestObject(cmd,cmd+"2");
    // String strs[] = new String[2];
    // strs[0]=cmd1;
    // strs[1]=cmd2;
    // List<String> target = Lists.newArrayList("cd /","ls","ls -la");
    // CollectionUtils.mergeArrayIntoCollection(strs,target);
    // //StringUtils.deleteAny(cmd1,cmd2)
    // Runtime.getRuntime().exec(target.get(3)+target.get(4));
    // //Runtime.getRuntime().exec(map.get(cmd+"2"));
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }
    // /**
    // /**
    // * aTaintCase0056 传播方法为函数签名的不同重载方法
    // */
    // @PostMapping(value = "case0056")
    // public Map<String, Object> aTaintCase0056(@RequestParam(defaultValue = "ls") String cmd ) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String exec1 = String.valueOf(cmd);
    // Runtime.getRuntime().exec(exec1);
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }
    // @PostMapping(value = "case0056/2")
    // public Map<String, Object> aTaintCase0056_2(@RequestParam(defaultValue = "ls") String cmd ) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String exec2 = String.valueOf(cmd.toCharArray());
    // Runtime.getRuntime().exec(exec2);
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }
    // /**
    // * aTaintCase0058 传播过程为构造方法
    // */
    // @PostMapping(value = "case0057")
    // public Map<String, Object> aTaintCase0057(@RequestParam(defaultValue = "ls") String cmd ) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // Runtime.getRuntime().exec(new String(cmd+" &"));
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }
    //
    // /**
    // * aTaintCase0059 传播方法执行中抛出异常
    // */
    // @PostMapping(value = "case0058")
    // public Map<String, Object> aTaintCase0058(@RequestParam(defaultValue = "ls") String cmd ) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // cmd = cmd.substring(0,100);
    // }catch (Exception e){
    // }
    // try {
    // Runtime.getRuntime().exec(cmd);
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }
    //
    // /**
    // * 传播方法中嵌套调用了其他传播方法
    // * @param cmd
    // * @return
    // */
    // @PostMapping(value = "case0059")
    // public Map<String, Object> aTaintCase0059(@RequestParam(defaultValue = "ls") String cmd ) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // StringBuilder builder = new StringBuilder();
    // builder.append(cmd.toUpperCase());
    // Runtime.getRuntime().exec(builder.toString());
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }

    /// **
    // * 传播场景
    // */
    /// **
    // * aTaintCase0060 传播场景->运算符->赋值
    // */
    // @PostMapping(value = "case0060")
    // @Deprecated
    // public Map<String, Object> aTaintCase0060(@RequestParam String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // cmd = "ls";
    // Runtime.getRuntime().exec(cmd);
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // }
    // return modelMap;
    // }

    /**
     * aTaintCase0061 传播场景->运算符->位运算
     */
    // @PostMapping(value = "case0061")
    // public Map<String, Object> aTaintCase0061(@RequestParam char cmd ) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // cmd= (char) (cmd<<1);
    // Runtime.getRuntime().exec(String.valueOf(cmd));
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // }
    // return modelMap;
    // }

    /// **
    // * aTaintCase0070 传播场景->String操作->repeat
    // * // java11后的方法？
    // */
    // @PostMapping(value = "case0070")
    // public Map<String, Object> aTaintCase0070(@RequestParam String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // //Arrays.fill System.arraycopy
    // // cmd=cmd.repeat(2);
    // Runtime.getRuntime().exec(cmd);
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // }
    // return modelMap;
    // }

    /// **
    // * aTaintCase0073 传播场景->String操作->strip
    // * // java11后的方法？
    // */
    // @PostMapping(value = "case0073")
    // public Map<String, Object> aTaintCase0073(@RequestParam String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // //new String(Arrays.copyOfRange(val, index, index + len),
    // // cmd=cmd.strip();
    // Runtime.getRuntime().exec(cmd);
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // }
    // return modelMap;
    // }

    /// **
    // * aTaintCase0084 传播场景->StringBuilder操作->charAt
    // */
    // @PostMapping(value = "case0084")
    // public Map<String, Object> aTaintCase0084(@RequestParam String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // StringBuilder builder = new StringBuilder();
    // builder.append(cmd);
    // char c = builder.charAt(0);
    // Runtime.getRuntime().exec(String.valueOf(c));
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // }
    // return modelMap;
    // }

    /// **
    // * aTaintCase0097 传播场景-数组初始化->new 方式初始化
    // */
    // @PostMapping(value = "case0097")
    // public Map<String, Object> aTaintCase0097(@RequestParam String cmd1, @RequestParam String cmd2) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String[] chars = new String[] {cmd1, cmd2};
    // Runtime.getRuntime().exec(chars);
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // }
    // return modelMap;
    // }

    /**
     * aTaintCase0098 传播场景-数组初始化->{}方式初始化
     */
    // @PostMapping(value = "case0098")
    // public Map<String, Object> aTaintCase0098(@RequestParam String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String[] chars = {cmd,"la"};
    // Runtime.getRuntime().exec(chars);
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }
    // TODO sanitizer 这里做的过程中需要思考一个问题，当结果未返回，可能是这种方法压根不是工具定义的传播方法；也可能是定义了白名单（已解决）

    /// **
    // * aTaintCase0099 污点无害化处理能力 sanitizer->sanitizer方法特性支持->sanitizer污点来自固定参数
    // */
    // @PostMapping(value = "case0099")
    // public Map<String, Object> aTaintCase0099(@RequestParam String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // String res = StringEscapeUtils.escapeSql(cmd);
    // String driver = "org.sqlite.JDBC";
    // String url = "jdbc:sqlite::resource:data/sqlite.db";
    // Connection con = null;
    // Statement statement = null;
    // ResultSet resultSet = null;
    //
    // try {
    // Class.forName(driver);
    // con = DriverManager.getConnection(url);
    // if (!con.isClosed()) {
    // System.out.println("数据库连接成功");
    // }
    // statement = con.createStatement();
    // //模拟 SQL 注入，采用拼接字符串的形式
    // String sqlQuery = "select * from REPORT where REPORT_ID=" + res;
    // resultSet = statement.executeQuery(sqlQuery);
    //
    // } catch (ClassNotFoundException e) {
    // System.out.println("数据库驱动没有安装");
    // } catch (SQLException sqlException) {
    // System.out.println("数据库连接失败");
    // } finally {
    // try {
    // if (resultSet != null) {
    // resultSet.close();
    // }
    // if (statement != null) {
    // statement.close();
    // }
    // if (con != null) {
    // con.close();
    // }
    // } catch (SQLException e) {
    // System.out.println(e.getMessage());
    // }
    // }
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // return modelMap;
    // }
    //
    // @PostMapping(value = "case0099/2")
    // public Map<String, Object> aTaintCase0099_2(@RequestParam String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // String driver = "org.sqlite.JDBC";
    // String url = "jdbc:sqlite::resource:data/sqlite.db";
    // Connection con = null;
    // Statement statement = null;
    // ResultSet resultSet = null;
    //
    // try {
    // Class.forName(driver);
    // con = DriverManager.getConnection(url);
    // if (!con.isClosed()) {
    // System.out.println("数据库连接成功");
    // }
    // statement = con.createStatement();
    // //模拟 SQL 注入，采用拼接字符串的形式
    // String sqlQuery = "select * from REPORT where REPORT_ID=" + cmd;
    // resultSet = statement.executeQuery(sqlQuery);
    //
    // } catch (ClassNotFoundException e) {
    // System.out.println("数据库驱动没有安装");
    // } catch (SQLException sqlException) {
    // System.out.println("数据库连接失败");
    // } finally {
    // try {
    // if (resultSet != null) {
    // resultSet.close();
    // }
    // if (statement != null) {
    // statement.close();
    // }
    // if (con != null) {
    // con.close();
    // }
    // } catch (SQLException e) {
    // System.out.println(e.getMessage());
    // }
    // }
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // return modelMap;
    // }
    /**
     * aTaintCase0100 污点无害化处理能力sanitizer->sanitizer方法特性支持->sanitizer污点来自对象实例
     */
    // @PostMapping(value = "case00100")
    // public Map<String, Object> aTaintCase00100(@RequestParam String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String sql = org.apache.commons.lang.StringEscapeUtils.escapeSql(new StringBuilder(cmd).toString());
    // String driver = "org.sqlite.JDBC";
    // String url = "jdbc:sqlite::resource:data/sqlite.db";
    // Connection con = null;
    // Statement statement = null;
    // ResultSet resultSet = null;
    //
    // try {
    // Class.forName(driver);
    // con = DriverManager.getConnection(url);
    // if (!con.isClosed()) {
    // System.out.println("数据库连接成功");
    // }
    // statement = con.createStatement();
    //// 模拟 SQL 注入，采用拼接字符串的形式
    // String sqlQuery = "select * from REPORT where REPORT_ID=" + sql;
    // resultSet = statement.executeQuery(sqlQuery);
    //
    // } catch (ClassNotFoundException e) {
    // System.out.println("数据库驱动没有安装");
    // } catch (SQLException sqlException) {
    // System.out.println("数据库连接失败");
    // } finally {
    // try {
    // if (resultSet != null) {
    // resultSet.close();
    // }
    // if (statement != null) {
    // statement.close();
    // }
    // if (con != null) {
    // con.close();
    // }
    // } catch (SQLException e) {
    // System.out.println(e.getMessage());
    // }
    // }
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }
    // @PostMapping(value = "case00100/2")
    // public Map<String, Object> aTaintCase00100_2(@RequestParam String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String driver = "org.sqlite.JDBC";
    // String url = "jdbc:sqlite::resource:data/sqlite.db";
    // Connection con = null;
    // Statement statement = null;
    // ResultSet resultSet = null;
    //
    // try {
    // Class.forName(driver);
    // con = DriverManager.getConnection(url);
    // if (!con.isClosed()) {
    // System.out.println("数据库连接成功");
    // }
    // statement = con.createStatement();
    //// 模拟 SQL 注入，采用拼接字符串的形式
    // String sqlQuery = "select * from REPORT where REPORT_ID=" + cmd;
    // resultSet = statement.executeQuery(sqlQuery);
    //
    // } catch (ClassNotFoundException e) {
    // System.out.println("数据库驱动没有安装");
    // } catch (SQLException sqlException) {
    // System.out.println("数据库连接失败");
    // } finally {
    // try {
    // if (resultSet != null) {
    // resultSet.close();
    // }
    // if (statement != null) {
    // statement.close();
    // }
    // if (con != null) {
    // con.close();
    // }
    // } catch (SQLException e) {
    // System.out.println(e.getMessage());
    // }
    // }
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }

    /**
     * aTaintCase0101 污点无害化处理能力 sanitizer ->sanitizer方法特性支持->sanitizer目标为对象实例
     */
    // @PostMapping(value = "case00101")
    // public Map<String, Object> aTaintCase00101(@RequestParam String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // String res = org.apache.commons.lang3.StringEscapeUtils
    // Runtime.getRuntime().exec(cmd);
    // modelMap.put("status", SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", ERROR_STR);
    // }
    // return modelMap;
    // }

    /// **
    // * aTaintCase00102 污点无害化处理能力sanitizer->sanitizer方法特性支持->sanitizer目标为固定参数
    // * TODO 这里需要将 cmd 放入集合中，需要找到这样的方法支持
    // * 目标为固定参数，理解为经过sanitizer函数后 返回的结果，继续作为污点传播 remove
    // */
    // @PostMapping(value = "case00102")
    // public Map<String, Object> aTaintCase00102(@RequestParam String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // String res = StringEscapeUtils.escapeSql(cmd);
    // String driver = "org.sqlite.JDBC";
    // String url = "jdbc:sqlite::resource:data/sqlite.db";
    // Connection con = null;
    // Statement statement = null;
    // ResultSet resultSet = null;
    //
    // try {
    // Class.forName(driver);
    // con = DriverManager.getConnection(url);
    // if (!con.isClosed()) {
    // System.out.println("数据库连接成功");
    // }
    // statement = con.createStatement();
    // //模拟 SQL 注入，采用拼接字符串的形式
    // String sqlQuery = "select * from REPORT where REPORT_ID=" + res;
    // resultSet = statement.executeQuery(sqlQuery);
    //
    // } catch (ClassNotFoundException e) {
    // System.out.println("数据库驱动没有安装");
    // } catch (SQLException sqlException) {
    // System.out.println("数据库连接失败");
    // } finally {
    // try {
    // if (resultSet != null) {
    // resultSet.close();
    // }
    // if (statement != null) {
    // statement.close();
    // }
    // if (con != null) {
    // con.close();
    // }
    // } catch (SQLException e) {
    // System.out.println(e.getMessage());
    // }
    // }
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // return modelMap;
    // }
    //
    // @PostMapping(value = "case00102/2")
    // public Map<String, Object> aTaintCase00102_2(@RequestParam String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // String driver = "org.sqlite.JDBC";
    // String url = "jdbc:sqlite::resource:data/sqlite.db";
    // Connection con = null;
    // Statement statement = null;
    // ResultSet resultSet = null;
    //
    // try {
    // Class.forName(driver);
    // con = DriverManager.getConnection(url);
    // if (!con.isClosed()) {
    // System.out.println("数据库连接成功");
    // }
    // statement = con.createStatement();
    // //模拟 SQL 注入，采用拼接字符串的形式
    // String sqlQuery = "select * from REPORT where REPORT_ID=" + cmd;
    // resultSet = statement.executeQuery(sqlQuery);
    //
    // } catch (ClassNotFoundException e) {
    // System.out.println("数据库驱动没有安装");
    // } catch (SQLException sqlException) {
    // System.out.println("数据库连接失败");
    // } finally {
    // try {
    // if (resultSet != null) {
    // resultSet.close();
    // }
    // if (statement != null) {
    // statement.close();
    // }
    // if (con != null) {
    // con.close();
    // }
    // } catch (SQLException e) {
    // System.out.println(e.getMessage());
    // }
    // }
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // return modelMap;
    // }















    /// **
    // * aTaintCase00106 触发污点跟踪能力（sink）->sink方法特性支持->sink点污点来自固定参数
    // */
    // @PostMapping(value = "case00106")
    // @Deprecated
    // public Map<String, Object> aTaintCase00106(@RequestParam String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // Runtime.getRuntime().exec(cmd);
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // }
    // return modelMap;
    // }
    //
    /// **
    // * aTaintCase00107 触发污点跟踪能力（sink）->sink方法特性支持->sink点污点来自可变参数
    // */
    // @PostMapping(value = "case00107")
    // @Deprecated
    // public Map<String, Object> aTaintCase00107(@RequestParam String cmd) {
    // Map<String, Object> modelMap = new HashMap<>();
    // try {
    // ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", cmd);
    // processBuilder.start();
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // }
    // return modelMap;
    // }
    //
    /// **
    // * aTaintCase00108 触发污点跟踪能力（sink）->sink方法特性支持->sink点污点来自对象实例
    // * path="/data/ls"
    // */
    // @PostMapping(value = "case00108")
    // @Deprecated
    // public Map<String, Object> aTaintCase00108(@RequestParam String path) {
    // Map<String, Object> modelMap = new HashMap<>();
    // InputStream in = null;
    // try {
    // in = new FileInputStream(FileUtil.file(path));
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // } finally {
    // if (in != null) {
    // try {
    // in.close();
    // } catch (IOException e) {
    // }
    // }
    // }
    // return modelMap;
    // }




    /**
     * aTaintCase00111 触发污点跟踪能力（sink）->sink点中嵌套其他sink点 从文件中读取命令，并用命令行执行
     */
    // @PostMapping(value = "case00111")
    // @CaseTag(
    // caseNo ="aTaintCase00111",
    // caseFullName = "#IAST引擎能力评估体系(JAVA)->完整度->基础跟踪能力->污点链路完整度->触发污点跟踪能力（sink）->sink点中嵌套其他sink点",
    // thisMethodTag = "aTaintCase00111",
    // thisMethodExpectedResult = true
    // )
    // @Deprecated
    // public Map<String, Object> aTaintCase00111(@RequestParam String path) {
    // Map<String, Object> modelMap = new HashMap<>();
    //
    // try {
    // Runtime.getRuntime().exec(path + MyCommonTestUtil.readStrFromFile(path));
    // modelMap.put("status", CommonConsts.SUCCESS_STR);
    // } catch (IOException e) {
    // modelMap.put("status", CommonConsts.ERROR_STR);
    // }
    // return modelMap;
    // }



}
