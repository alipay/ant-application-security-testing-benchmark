package com.iast.astbenchmark.cases;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.iast.astbenchmark.cases.bean.SourceTestObject;
import com.iast.astbenchmark.cases.bean.xml.TicketRequest;
import com.iast.astbenchmark.common.utils.JDKSerializationUtil;
import com.iast.astbenchmark.common.utils.MyCommonTestUtil;
import com.iast.astbenchmark.common.CommonConsts;
import lombok.Data;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础跟踪能力->污点链路完整度（case0022～case00112）
 */
@RestController()
public class AstTaintCase002 {

    /**  污点链路完整度*/
    /**
     * 特殊链路跟踪能力->三方包方法跟踪
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0022/{cmd}")
    public Map<String, Object> aTaintCase0022(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(StrUtil.cleanBlank(cmd));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 特殊链路跟踪能力->超长链路追踪
     *
     * @param cmd
     * @return
     */
    @PostMapping("case0023")
    public Map<String, Object> aTaintCase0023(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", CommonConsts.ERROR_STR);
            return modelMap;
        }
        try {
            /** 递归100次*/
            cmd = MyCommonTestUtil.traceDeepth(cmd, 0, 100);
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 特殊链路跟踪能力->超长链路追踪
     *
     * @param cmd
     * @return
     */
    @PostMapping("case00931")
    public Map<String, Object> aTaintCase00931(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", CommonConsts.ERROR_STR);
            return modelMap;
        }
        try {
            /** 递归1000次*/
            cmd = MyCommonTestUtil.traceDeepth(cmd, 0, 1000);
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * 特殊链路跟踪能力->反射调用
     * @param cmd
     * @return
     */
    @PostMapping("case0024/{cmd}")
    public Map<String, Object> aTaintCase0024(@PathVariable String cmd)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", CommonConsts.ERROR_STR);
            return modelMap;
        }
        try {
            Class<StringBuilder> clazz = StringBuilder.class;
            Method method = clazz.getMethod("replace",int.class,int.class,String.class);
            method.setAccessible(true);
            StringBuilder builder = new StringBuilder(cmd);
            Runtime.getRuntime().exec(method.invoke(builder, 0,1,"l").toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }


    /**
     * 特殊链路跟踪能力->调用native 方法
     * @param cmd
     * @return
     */
    @PostMapping("case0025")
    public Map<String, Object> aTaintCase0025(@RequestBody String[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", CommonConsts.ERROR_STR);
            return modelMap;
        }
        try {
            //String[] b = {"ls","b"};
            String[] b = {"oo"};
            System.arraycopy(cmd,0,b,0,1);
            Runtime.getRuntime().exec(b);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }


    /**
     * 特殊链路跟踪能力->static方法追踪
     *
     * @param cmd
     * @return
     */
//    @PostMapping("case0026/{cmd}")
//    public Map<String, Object> aTaintCase0026(@PathVariable String cmd) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            Runtime.getRuntime().exec(StringUtils.deleteAny(cmd,"a"));
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }

    /** 污点来源识别能力*/

    /**
     * 污点来自http url getQueryString
     *
     * @param
     * @return
     */
    @PostMapping("case0027")
    public Map<String, Object> aTaintCase0027(HttpServletRequest request, @RequestParam String data) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(request.getQueryString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }


    /**
     * 污点来自http url getContenPath
     *
     * @param
     * @return
     */
    @PostMapping("case0028")
    public Map<String, Object> aTaintCase0028(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String datas = request.getContextPath();
            Runtime.getRuntime().exec(datas);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 污点来自http url getRequestURL
     *
     * @param
     * @return
     */
    @PostMapping("case0029")
    public Map<String, Object> aTaintCase0029(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String datas = String.valueOf(request.getRequestURL());
            Runtime.getRuntime().exec(datas);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 污点来自http url getServletPath
     *
     * @param
     * @return
     */
    @PostMapping("case0030")
    public Map<String, Object> aTaintCase0030(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String datas = request.getServletPath();
            Runtime.getRuntime().exec(datas);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 污点来自http url getRequestURI
     *
     * @param
     * @return
     */
    @PostMapping("case0031")
    public Map<String, Object> aTaintCase0031(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String datas = request.getRequestURI();
            Runtime.getRuntime().exec(datas);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 污点来自http url getPathInfo
     *
     * @param
     * @return
     */
    @PostMapping("case0032")
    public Map<String, Object> aTaintCase0032(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String datas = request.getPathInfo();
            Runtime.getRuntime().exec(datas);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }


    /**
     * 污点来自http body  *json
     *
     * @param {"cmd":"ls"}
     * @return //TODO
     */
    @PostMapping("case0033")
    public Map<String, Object> aTaintCase0033(@RequestBody Map<String,String> json) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(json.get(json.keySet().iterator().next()));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 污点来自http body  *xml  contentye==xml
     * @param /Users/curry/IdeaProjects/astbenchmark/src/main/resources/data/case0034.xml
     * @return
     */
//    @PostMapping("case0034")
//    public Map<String, Object> aTaintCase0034(MultipartFile file) throws IOException {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            Document document = XmlUtil.readXML(file.getInputStream());
//            Object obj = XmlUtil.getByXPath("//cmd",document, XPathConstants.STRING);
//            Runtime.getRuntime().exec(String.valueOf(obj));
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }

    @PostMapping(value = "/case0034", consumes = { MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public Map<String, Object> aTaintCase0034(@RequestBody TicketRequest ticketRequest){
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(ticketRequest.getOrderList().get(0).getPhase());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 污点来自http body  multipart/form-data Part
     *
     * @param
     * @return
     */
    @PostMapping(value = "case0035")
    public Map<String, Object> aTaintCase0035(@RequestParam MultipartFile file,HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
//            String cmd = request.getPart("file").getSubmittedFileName();
            Runtime.getRuntime().exec(request.getPart("file").getSubmittedFileName());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        return modelMap;
    }

    /**
     * 污点来自http body  multipart/form-data Parts()
     *
     * @param
     * @return
     */
    @PostMapping(value = "case0036")
    public Map<String, Object> aTaintCase0036(@RequestParam MultipartFile file,HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String cmd = request.getParts().iterator().next().getSubmittedFileName();
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        return modelMap;
    }

    /**
     * 污点来自http body  form/url-encode getParameter
     *
     * @param
     * @return
     */
    @PostMapping(value = "case0037")
    public Map<String, Object> aTaintCase0037(@RequestParam("cmd") String cmd, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String cmdStr = request.getParameter("cmd");
            Runtime.getRuntime().exec(cmdStr);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 污点来自http body  form/url-encode getParameterMap
     * @param
     * @return
     */
    @PostMapping(value = "case0038")
    public Map<String, Object> aTaintCase0038(@RequestParam("cmd") String cmd, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String cmdStr = request.getParameterMap().get("cmd")[0];
            Runtime.getRuntime().exec(cmdStr);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 污点来自http body  form/url-encode getParameterValues
     *
     * @param
     * @return
     */
    @PostMapping(value = "case0039")
    public Map<String, Object> aTaintCase0039(@RequestParam("cmd") String cmd, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            //String cmdStr = request.getParameterValues("cmd")[0];
            Runtime.getRuntime().exec(request.getParameterValues("cmd"));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 污点来自http body  form/url-encode getParameterNames
     *
     * @param
     * @return
     */
    @PostMapping(value = "case0040")
    public Map<String, Object> aTaintCase0040(@RequestParam("cmd") String cmd, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String cmdStr = request.getParameterNames().nextElement();
            Runtime.getRuntime().exec(cmdStr);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 污点来自http body  getReader
     *    测试用例创建文件
     * @param /Users/curry/IdeaProjects/astbenchmark/src/main/resources/data/ls
     * @return
     */
    @PostMapping(value = "case0041")
    public Map<String, Object> aTaintCase0041(@RequestBody String[] cmd, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            int len = request.getContentLength();
           // ServletInputStream in = request.getInputStream();
            String cmdStr = "";
            if (len != -1) {
                BufferedReader reader = request.getReader();
                String str ;
                while ((str = reader.readLine()) != null) {
                    cmdStr += str;
                }
                reader.close();
            }
            Runtime.getRuntime().exec(cmdStr);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

//    /**
//     * 污点来自http pathVarlables参数值数字
//     * @param cmd ls
//     * @return
//     */
//    @PostMapping(value = "case0042/{cmd}")
//    public Map<String, Object> aTaintCase0042(@PathVariable String cmd) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            Runtime.getRuntime().exec(cmd);
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }
//
//    /**
//     * 污点来自http pathVarlables参数值字符串
//     * @param cmd aa
//     * @return
//     */
//    @PostMapping(value = "case0043/{cmd}")
//    public Map<String, Object> aTaintCase0043(@PathVariable String cmd) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            Runtime.getRuntime().exec(cmd);
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }

    /**
     * 污点来自http pathVarlables参数值字母和数字的组合
     * @param cmd a1
     * @return
     */
    @PostMapping(value = "case0044/{cmd}")
    public Map<String, Object> aTaintCase0044(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }


    /**
     * 污点来自http headers   getCookies
     *
     * @param
     * @return
     */
    @PostMapping(value = "case0045")
    public Map<String, Object> aTaintCase0045( HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Cookie[] cookies = request.getCookies();
            Runtime.getRuntime().exec(String.valueOf( cookies[0].getName()));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 污点来自http headers   getHeader
     *
     * @param
     * @return
     */
    @PostMapping(value = "case0046")
    public Map<String, Object> aTaintCase0046(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String cmd  = request.getHeader("cmd");
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 污点来自http headers   getHeaders
     *
     * @param
     * @return
     */
    @PostMapping(value = "case0047")
    public Map<String, Object> aTaintCase0047( HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String cmd  = request.getHeaders("cmd").nextElement();
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * 污点来自http headers   getHeaderNames
     * @param request
     * @return
     */
    @PostMapping(value = "case00139")
    public Map<String, Object> aTaintCase00139(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String cmd  = request.getHeaderNames().nextElement();
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /** 污点传播跟踪能力*/

//    /**
//     * 污点来自固定参数
//     *
//     * @param
//     * @return
//     */
//    @PostMapping(value = "case0048")
//    public Map<String, Object> aTaintCase0048(@RequestParam(defaultValue = "ls") String cmd) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            String exec = String.valueOf(cmd);
//            Runtime.getRuntime().exec(exec);
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }
//
//    /**
//     * 污点来自可变参数
//     * @param
//     * @return
//     */
//    @PostMapping(value = "case0049")
//    public Map<String, Object> aTaintCase0049(@RequestParam String cmd) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            Runtime.getRuntime().exec(String.format("%s -la",cmd));
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }
//
//
//    /**
//     * 污点来自对象实例
//     *
//     * @param
//     * @return
//     */
//    @PostMapping(value = "case0050")
//    public Map<String, Object> aTaintCase0050(@RequestBody SourceTestObject source  ) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            String exec = source.getCmd();
//            Runtime.getRuntime().exec(exec);
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }
//
//    /**
//     *
//     * 污点来自多个参数
//     * @param
//     * @return
//     */
//    @PostMapping(value = "case0051")
//    public Map<String, Object> aTaintCase0051(@RequestParam String cmd1,@RequestParam String cmd2  ) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            Runtime.getRuntime().exec( String.format("%s -%s",cmd1,cmd2));
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }
//
//    /**
//     * 污点传播目标为某个固定参数
//     * TODO  Map  这里用的 listcpoy   再找下
//     * @param
//     * @return
//     */
//    @PostMapping(value = "case0052")
//    public Map<String, Object> aTaintCase0052(@RequestParam(defaultValue = "ls") String cmd ) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            String strs[] = new String[1];
//            strs[0]=cmd;
//            List<String> target = Lists.newArrayList("cd /","ls","ls -la");
//            CollectionUtils.mergeArrayIntoCollection(strs,target);
//            Runtime.getRuntime().exec(target.get(3));
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }
//
//    /**
//     * 污点传播目标为方法返回值
//     *
//     * @param
//     * @return
//     */
//    @PostMapping(value = "case0053")
//    public Map<String, Object> aTaintCase0053(@RequestParam(defaultValue = "ls") String cmd ) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            String exec = String.format("%s -la",cmd);
//            Runtime.getRuntime().exec(exec);
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }
//
//    /**
//     * 污点传播目标为对象实例
//     * @param
//     * @return
//     */
//    @PostMapping(value = "case0054")
//    public Map<String, Object> aTaintCase0054(@RequestBody SourceTestWithConstract01Bean bean) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            SourceTestWithConstract01Bean bean1 = MyCommonTestUtil.buildTestObject("cd ~");
//            SourceTestWithConstract01Bean bean2 = MyCommonTestUtil.buildTestObject("cd /");
//            SourceTestWithConstract01Bean[] beans = new SourceTestWithConstract01Bean[1];
//            beans[0]=bean;
//            List<SourceTestWithConstract01Bean> target = Lists.newArrayList(bean1,bean2);
//            CollectionUtils.mergeArrayIntoCollection(beans,target);
//            Runtime.getRuntime().exec(target.get(2).getCmd());
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }
//
//    /**
//     * 污点传播目标为多个参数
//     *  //TODO   一个污点传播到多个target
//     * @param
//     * @return
//     */
//    @PostMapping(value = "case0055")
//    public Map<String, Object> aTaintCase0055(@RequestParam(defaultValue = "ls") String cmd1,@RequestParam(defaultValue = "ls") String cmd2 ) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            //Map<String,String> map = MyCommonTestUtil.buildTestObject(cmd,cmd+"2");
//            String strs[] = new String[2];
//            strs[0]=cmd1;
//            strs[1]=cmd2;
//            List<String> target = Lists.newArrayList("cd /","ls","ls -la");
//            CollectionUtils.mergeArrayIntoCollection(strs,target);
//            //StringUtils.deleteAny(cmd1,cmd2)
//            Runtime.getRuntime().exec(target.get(3)+target.get(4));
//            //Runtime.getRuntime().exec(map.get(cmd+"2"));
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }
//    /**
//    /**
//     * aTaintCase0056 传播方法为函数签名的不同重载方法
//     */
//    @PostMapping(value = "case0056")
//    public Map<String, Object> aTaintCase0056(@RequestParam(defaultValue = "ls") String cmd ) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            String exec1 = String.valueOf(cmd);
//            Runtime.getRuntime().exec(exec1);
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }
//    @PostMapping(value = "case0056/2")
//    public Map<String, Object> aTaintCase0056_2(@RequestParam(defaultValue = "ls") String cmd ) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            String exec2 = String.valueOf(cmd.toCharArray());
//            Runtime.getRuntime().exec(exec2);
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }
//    /**
//     * aTaintCase0058 传播过程为构造方法
//     */
//    @PostMapping(value = "case0057")
//    public Map<String, Object> aTaintCase0057(@RequestParam(defaultValue = "ls") String cmd ) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            Runtime.getRuntime().exec(new String(cmd+" &"));
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }
//
//    /**
//     * aTaintCase0059 传播方法执行中抛出异常
//     */
//    @PostMapping(value = "case0058")
//    public Map<String, Object> aTaintCase0058(@RequestParam(defaultValue = "ls") String cmd ) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//             cmd = cmd.substring(0,100);
//        }catch (Exception e){
//        }
//        try {
//            Runtime.getRuntime().exec(cmd);
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }
//
//    /**
//     * 传播方法中嵌套调用了其他传播方法
//     * @param cmd
//     * @return
//     */
//    @PostMapping(value = "case0059")
//    public Map<String, Object> aTaintCase0059(@RequestParam(defaultValue = "ls") String cmd ) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            StringBuilder builder = new StringBuilder();
//            builder.append(cmd.toUpperCase());
//            Runtime.getRuntime().exec(builder.toString());
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }


    /**
     * 传播场景
     */
    /**
     * aTaintCase0060 传播场景->运算符->赋值
     */
    @PostMapping(value = "case0060")
    public Map<String, Object> aTaintCase0060(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd= "ls";
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * aTaintCase0061 传播场景->运算符->位运算
     */
    //@PostMapping(value = "case0061")
    //public Map<String, Object> aTaintCase0061(@RequestParam char cmd ) {
    //    Map<String, Object> modelMap = new HashMap<>();
    //    try {
    //        cmd= (char) (cmd<<1);
    //        Runtime.getRuntime().exec(String.valueOf(cmd));
    //        modelMap.put("status", CommonConsts.SUCCESS_STR);
    //    } catch (IOException e) {
    //        modelMap.put("status", CommonConsts.ERROR_STR);
    //    }
    //    return modelMap;
    //}
    /**
     * aTaintCase0062 传播场景->String操作->构造方法
     */
    @PostMapping(value = "case0062")
    public Map<String, Object> aTaintCase0062(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            //SourceTestWithConstract01Bean constractBean = new SourceTestWithConstract01Bean(cmd);
            Runtime.getRuntime().exec(new String(cmd));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping(value = "case00143")
    public Map<String, Object> aTaintCase00143(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(new String(cmd.toCharArray()));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping(value = "case00144")
    public Map<String, Object> aTaintCase00144(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(new String(cmd.toCharArray(),0,2));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * @param codePoints
     * @return
     */
    @PostMapping(value = "case00145")
    public Map<String, Object> aTaintCase00145(@RequestBody int[]  codePoints) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            //String testCode = UnicodeUtil.toUnicode(cmd);
//            int[] codePoints = new int[cmd.length()];
//            int cpCount = cmd.codePointCount(0, cmd.length());
//            for(int index = 0; index < cpCount; ++index) {
//                //这里的i是字符的位置
//                int i = cmd.offsetByCodePoints(0, index);
//                int codepoint = cmd.codePointAt(i);
//                codePoints[i]=codepoint;
//                //108 105
//            }
            Runtime.getRuntime().exec(new String(codePoints,0,2));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00146")
    public Map<String, Object> aTaintCase00146(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            byte[] bytes = cmd.getBytes();
            Runtime.getRuntime().exec(new String(bytes,0,bytes.length,"utf-8"));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping(value = "case00147")
    public Map<String, Object> aTaintCase00147(@RequestBody byte[] bytes ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
          //  byte[] bytes = cmd.getBytes();
            Runtime.getRuntime().exec(new String(bytes,0,bytes.length, Charset.forName("utf-8")));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00148")
    public Map<String, Object> aTaintCase00148(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuffer buffer = new StringBuffer(cmd);
            Runtime.getRuntime().exec(new String(buffer));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    @PostMapping(value = "case00149")
    public Map<String, Object> aTaintCase00149(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder buffer = new StringBuilder(cmd);
            Runtime.getRuntime().exec(new String(buffer));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }


    /**
     * aTaintCase0063 传播场景->String操作->conact
     */
    @PostMapping(value = "case0063")
    public Map<String, Object> aTaintCase0063(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.concat(" -la");
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase0064 传播场景->String操作->copyValueOf
     */
    @PostMapping(value = "case0064")
    public Map<String, Object> aTaintCase0064(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            char data[] =  cmd.toCharArray();
            Runtime.getRuntime().exec(String.copyValueOf(data));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase0065 传播场景->String操作->format
     */
    @PostMapping(value = "case0065")
    public Map<String, Object> aTaintCase0065(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd =String.format("%s -la",cmd);
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase0066 传播场景->String操作->getBytes
     */

    @PostMapping(value = "case0066")
    public Map<String, Object> aTaintCase0066(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            byte[] bytes = cmd.getBytes();
            Runtime.getRuntime().exec(new String(bytes));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * aTaintCase0067 传播场景->String操作->getChars
     */
    @PostMapping(value = "case0067")
    public Map<String, Object> aTaintCase0067(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            char[] chars= new char[]{0,0};
            cmd.getChars(0,2,chars,0);
            Runtime.getRuntime().exec(new String(chars));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase0068 传播场景->String操作->intern
     */
    @PostMapping(value = "case0068")
    public Map<String, Object> aTaintCase0068(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.intern());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase0069 传播场景->String操作->join
     */
    @PostMapping(value = "case0069")
    public Map<String, Object> aTaintCase0069(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=String.join(" ",cmd,"-la");
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }


    /**
     * aTaintCase0070 传播场景->String操作->repeat
     * // java11后的方法？
     */
    @PostMapping(value = "case0070")
    public Map<String, Object> aTaintCase0070(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            //Arrays.fill System.arraycopy
            // cmd=cmd.repeat(2);
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * aTaintCase0071 传播场景->String操作->replace
     * ls;-la
     */
    @PostMapping(value = "case0071")
    public Map<String, Object> aTaintCase0071(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.replace(";"," ");
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     * aTaintCase00140 传播场景->String操作->replace
     * alasa
     */
    @PostMapping(value = "case00140")
    public Map<String, Object> aTaintCase00140(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.replaceAll("a","");
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase0072 传播场景->String操作->split
     */
    @PostMapping(value = "case0072")
    public Map<String, Object> aTaintCase0072(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.split(" ")[0];
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase0073 传播场景->String操作->strip
     * // java11后的方法？
     */
    @PostMapping(value = "case0073")
    public Map<String, Object> aTaintCase0073(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            //new String(Arrays.copyOfRange(val, index, index + len),
            // cmd=cmd.strip();
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase0074 传播场景->String操作->subSequence
     */
    @PostMapping(value = "case0074")
    public Map<String, Object> aTaintCase0074(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd.subSequence(0,2)));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase0075 传播场景->String操作->substring
     * lsabc
     */
    @PostMapping(value = "case0075")
    public Map<String, Object> aTaintCase0075(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.substring(0,2));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase0076 传播场景->String操作->toCharArray
     */
    @PostMapping(value = "case0076")
    public Map<String, Object> aTaintCase0076(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            char[] chars=cmd.toCharArray();
            Runtime.getRuntime().exec(String.valueOf(chars));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase0077 传播场景->String操作->toLowerCase
     */
    @PostMapping(value = "case0077")
    public Map<String, Object> aTaintCase0077(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.toLowerCase();
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase0078 传播场景->String操作->toString
     */
    @PostMapping(value = "case0078")
    public Map<String, Object> aTaintCase0078(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.toString();
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase0079 传播场景->String操作->toUpperCase
     */
    @PostMapping(value = "case0079")
    public Map<String, Object> aTaintCase0079(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.toUpperCase();
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase0080 传播场景->String操作->trim
     */

    @PostMapping(value = "case0080")
    public Map<String, Object> aTaintCase0080(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.trim();
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase0081 传播场景->String操作->valueOf
     */
    @PostMapping(value = "case0081")
    public Map<String, Object> aTaintCase0081(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=String.valueOf(cmd);
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     *aTaintCase0082 传播场景->StringBuilder操作->构造方法
     */
    @PostMapping(value = "case0082")
    public Map<String, Object> aTaintCase0082(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            //SourceTestWithConstract02Bean constract02Bean = new SourceTestWithConstract02Bean();
            Runtime.getRuntime().exec(String.valueOf(new StringBuilder(cmd)));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     *aTaintCase0083 传播场景->StringBuilder操作->append
     */
    @PostMapping(value = "case0083")
    public Map<String, Object> aTaintCase0083(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     *aTaintCase0084 传播场景->StringBuilder操作->charAt
     *
     */
    @PostMapping(value = "case0084")
    public Map<String, Object> aTaintCase0084(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            char c= builder.charAt(0);
            Runtime.getRuntime().exec(String.valueOf(c));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     *aTaintCase0085 传播场景->StringBuilder操作->delete
     */
    @PostMapping(value = "case0085")
    public Map<String, Object> aTaintCase0085(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            builder.delete(2,cmd.length());
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     *aTaintCase0086 传播场景->StringBuilder操作->deleteCharAt
     */
    @PostMapping(value = "case0086")
    public Map<String, Object> aTaintCase0086(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            builder.deleteCharAt(2);
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     *aTaintCase0087 传播场景->StringBuilder操作->getChars
     */
    @PostMapping(value = "case0087")
    public Map<String, Object> aTaintCase0087(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            char[] chars = {0,0};
            builder.getChars(0,2,chars,0);
            Runtime.getRuntime().exec(String.valueOf(chars));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     *aTaintCase0088 传播场景->StringBuilder操作->insert
     */
    @PostMapping(value = "case0088")
    public Map<String, Object> aTaintCase0088(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.insert(0,cmd);
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     *aTaintCase0089 传播场景->StringBuilder操作->replace
     */
    @PostMapping(value = "case0089")
    public Map<String, Object> aTaintCase0089(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder(cmd);
            builder.replace(2,3,cmd);
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     *aTaintCase0090 传播场景->StringBuilder操作->subSequence
     */
    @PostMapping(value = "case0090")
    public Map<String, Object> aTaintCase0090(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            builder.subSequence(0,2);
            Runtime.getRuntime().exec(String.valueOf(builder.subSequence(0,2)));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     *aTaintCase0091 传播场景->StringBuilder操作->subString
     */
    @PostMapping(value = "case0091")
    public Map<String, Object> aTaintCase0091(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            Runtime.getRuntime().exec(builder.substring(0,2));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     *aTaintCase0092 传播场景->StringBuilder操作->toString
     */
    @PostMapping(value = "case0092")
    public Map<String, Object> aTaintCase0092(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     *aTaintCase0093 传播场景-char[],byte[]操作->copyOf
     */
    @PostMapping(value = "case0093")
    public Map<String, Object> aTaintCase0093(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            byte[] b1 = cmd.getBytes();
            byte[] b2 = Arrays.copyOf(b1,10);
            Runtime.getRuntime().exec(new String(b2));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     *aTaintCase0094 传播场景-char[],byte[]操作-->copyOfRange
     */
    @PostMapping(value = "case0094")
    public Map<String, Object> aTaintCase0094(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            byte[] b1 = cmd.getBytes();
            byte[] b2 = Arrays.copyOfRange(b1,0,2);
            Runtime.getRuntime().exec(new String(b2));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase0095 传播场景-char[],byte[]操作->deepToString
     * //deepToString  的参数是Object[] *使用Byte[]
     */
    @PostMapping(value = "case0095")
    public Map<String, Object> aTaintCase0095(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Byte[] bytes = MyCommonTestUtil.toObjects(cmd.getBytes());
            String str2 = Arrays.deepToString(bytes);
            Runtime.getRuntime().exec(str2);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     *aTaintCase0096 传播场景-char[],byte[]操作->toString
     */
    @PostMapping(value = "case0096")
    public Map<String, Object> aTaintCase0096(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            char[] chars = cmd.toCharArray();
            Runtime.getRuntime().exec(chars.toString());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

    /**
     *aTaintCase0097 传播场景-数组初始化->new 方式初始化
     */
    @PostMapping(value = "case0097")
    public Map<String, Object> aTaintCase0097(@RequestParam String cmd1,@RequestParam String cmd2) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] chars = new String[]{cmd1,cmd2};
            Runtime.getRuntime().exec(chars);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    @PostMapping(value = "case00932")
    public Map<String, Object> aTaintCase00932(@RequestBody SourceTestObject sourceTestObject) throws ClassNotFoundException {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            byte[] bytes = JDKSerializationUtil.serialize(sourceTestObject);
            SourceTestObject testObject = JDKSerializationUtil.deSerialize(bytes);
            Runtime.getRuntime().exec(testObject.getCmd());
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     *aTaintCase0098 传播场景-数组初始化->{}方式初始化
     */
//    @PostMapping(value = "case0098")
//    public Map<String, Object> aTaintCase0098(@RequestParam String cmd) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            String[] chars = {cmd,"la"};
//            Runtime.getRuntime().exec(chars);
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }
    //TODO sanitizer  这里做的过程中需要思考一个问题，当结果未返回，可能是这种方法压根不是工具定义的传播方法；也可能是定义了白名单（已解决）
    /**
     * aTaintCase0099 污点无害化处理能力 sanitizer->sanitizer方法特性支持->sanitizer污点来自固定参数
     *
     */
    @PostMapping(value = "case0099")
    public Map<String, Object> aTaintCase0099(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String res = StringEscapeUtils.escapeSql(cmd);
        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite::resource:data/sqlite.db";
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
            statement = con.createStatement();
//模拟 SQL 注入，采用拼接字符串的形式
            String sqlQuery = "select * from REPORT where REPORT_ID=" + res;
            resultSet = statement.executeQuery(sqlQuery);

        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException sqlException) {
            System.out.println("数据库连接失败");
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }
    @PostMapping(value = "case0099/2")
    public Map<String, Object> aTaintCase0099_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite::resource:data/sqlite.db";
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
            statement = con.createStatement();
//模拟 SQL 注入，采用拼接字符串的形式
            String sqlQuery = "select * from REPORT where REPORT_ID=" + cmd;
            resultSet = statement.executeQuery(sqlQuery);

        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException sqlException) {
            System.out.println("数据库连接失败");
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }
    /**
     * aTaintCase0100 污点无害化处理能力sanitizer->sanitizer方法特性支持->sanitizer污点来自对象实例
     */
//    @PostMapping(value = "case00100")
//    public Map<String, Object> aTaintCase00100(@RequestParam String cmd) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            String sql = org.apache.commons.lang.StringEscapeUtils.escapeSql(new StringBuilder(cmd).toString());
//            String driver = "org.sqlite.JDBC";
//            String url = "jdbc:sqlite::resource:data/sqlite.db";
//            Connection con = null;
//            Statement statement = null;
//            ResultSet resultSet = null;
//
//            try {
//                Class.forName(driver);
//                con = DriverManager.getConnection(url);
//                if (!con.isClosed()) {
//                    System.out.println("数据库连接成功");
//                }
//                statement = con.createStatement();
////模拟 SQL 注入，采用拼接字符串的形式
//                String sqlQuery = "select * from REPORT where REPORT_ID=" + sql;
//                resultSet = statement.executeQuery(sqlQuery);
//
//            } catch (ClassNotFoundException e) {
//                System.out.println("数据库驱动没有安装");
//            } catch (SQLException sqlException) {
//                System.out.println("数据库连接失败");
//            } finally {
//                try {
//                    if (resultSet != null) {
//                        resultSet.close();
//                    }
//                    if (statement != null) {
//                        statement.close();
//                    }
//                    if (con != null) {
//                        con.close();
//                    }
//                } catch (SQLException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }
//    @PostMapping(value = "case00100/2")
//    public Map<String, Object> aTaintCase00100_2(@RequestParam String cmd) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            String driver = "org.sqlite.JDBC";
//            String url = "jdbc:sqlite::resource:data/sqlite.db";
//            Connection con = null;
//            Statement statement = null;
//            ResultSet resultSet = null;
//
//            try {
//                Class.forName(driver);
//                con = DriverManager.getConnection(url);
//                if (!con.isClosed()) {
//                    System.out.println("数据库连接成功");
//                }
//                statement = con.createStatement();
////模拟 SQL 注入，采用拼接字符串的形式
//                String sqlQuery = "select * from REPORT where REPORT_ID=" + cmd;
//                resultSet = statement.executeQuery(sqlQuery);
//
//            } catch (ClassNotFoundException e) {
//                System.out.println("数据库驱动没有安装");
//            } catch (SQLException sqlException) {
//                System.out.println("数据库连接失败");
//            } finally {
//                try {
//                    if (resultSet != null) {
//                        resultSet.close();
//                    }
//                    if (statement != null) {
//                        statement.close();
//                    }
//                    if (con != null) {
//                        con.close();
//                    }
//                } catch (SQLException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }




    /**
     * aTaintCase0101 污点无害化处理能力 sanitizer ->sanitizer方法特性支持->sanitizer目标为对象实例
     */
//    @PostMapping(value = "case00101")
//    public Map<String, Object> aTaintCase00101(@RequestParam String cmd) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            String res = org.apache.commons.lang3.StringEscapeUtils
//            Runtime.getRuntime().exec(cmd);
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }
    /**
     * aTaintCase00102 污点无害化处理能力sanitizer->sanitizer方法特性支持->sanitizer目标为固定参数
     * TODO   这里需要将　cmd 放入集合中，需要找到这样的方法支持
     *  目标为固定参数，理解为经过sanitizer函数后 返回的结果，继续作为污点传播 remove
     */
    @PostMapping(value = "case00102")
    public Map<String, Object> aTaintCase00102(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String res = StringEscapeUtils.escapeSql(cmd);
        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite::resource:data/sqlite.db";
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
            statement = con.createStatement();
//模拟 SQL 注入，采用拼接字符串的形式
            String sqlQuery = "select * from REPORT where REPORT_ID=" + res;
            resultSet = statement.executeQuery(sqlQuery);

        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException sqlException) {
            System.out.println("数据库连接失败");
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }
    @PostMapping(value = "case00102/2")
    public Map<String, Object> aTaintCase00102_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite::resource:data/sqlite.db";
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
            statement = con.createStatement();
//模拟 SQL 注入，采用拼接字符串的形式
            String sqlQuery = "select * from REPORT where REPORT_ID=" + cmd;
            resultSet = statement.executeQuery(sqlQuery);

        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException sqlException) {
            System.out.println("数据库连接失败");
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    /**
     * 污点直接赋值为硬编码值 (硬编码是一种sanitizer方式)
     * @param cmd
     * @return
     */
    @PostMapping(value = "case00141")
    public Map<String, Object> aTaintCase00141(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        cmd="testId";
        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite::resource:data/sqlite.db";
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
            statement = con.createStatement();
//模拟 SQL 注入，采用拼接字符串的形式
            String sqlQuery = "select * from REPORT where REPORT_ID=" + cmd;
            resultSet = statement.executeQuery(sqlQuery);

        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException sqlException) {
            System.out.println("数据库连接失败");
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }

    /**
     * aTaintCase00103 污点无害化处理能力sanitizer->sanitizer支持区分类型
     */

//    @PostMapping(value = "case00103")
//    public HttpServletResponse aTaintCase00103(@RequestParam String cmd, HttpServletRequest request, HttpServletResponse response) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            String res = org.springframework.web.util.HtmlUtils.htmlEscape(cmd);
//            StringWriter stringWriter = new StringWriter();
//            stringWriter.append(res);
//            BufferedWriter writer = new BufferedWriter(stringWriter,100);
//           // writer.append(res);
//
//            com.sun.faces.renderkit.html_basic.HtmlResponseWriter htmlWriter = new com.sun.faces.renderkit.html_basic.HtmlResponseWriter(response.getWriter(),"text/html", "UTF-8");
//            htmlWriter.append(cmd);
//            htmlWriter.flush();
//            //Runtime.getRuntime().exec(res);
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return response;
//    }
    @PostMapping(value = "case00103/2")
    public Map<String, Object> aTaintCase00103_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            //这里使用javaScriptEscape
            //String res = org.springframework.web.util.JavaScriptUtils.javaScriptEscape(cmd);
            String res = org.springframework.web.util.HtmlUtils.htmlEscape(cmd);
            Runtime.getRuntime().exec(res);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
//    @PostMapping(value = "case00103/3")
//    public Map<String, Object> aTaintCase00103_3(@RequestParam String cmd) {
//        Map<String, Object> modelMap = new HashMap<>();
//        try {
//            StringWriter stringWriter = new StringWriter();
//            stringWriter.append(cmd);
//            BufferedWriter writer = new BufferedWriter(stringWriter,100);
//            com.sun.faces.renderkit.html_basic.HtmlResponseWriter htmlWriter = new com.sun.faces.renderkit.html_basic.HtmlResponseWriter(new BufferedWriter(writer),"HTML","UTF-8");
//            htmlWriter.flush();
//            htmlWriter.close();
//            modelMap.put("status", SUCCESS_STR);
//        } catch (IOException e) {
//            modelMap.put("status", ERROR_STR);
//        }
//        return modelMap;
//    }

    /**
     * aTaintCase00104 污点无害化处理能力sanitizer->触发sink后再执行sanitizer
     */
    @PostMapping(value = "case00104")
    public Map<String, Object> aTaintCase00104(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite::resource:data/sqlite.db";
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
            statement = con.createStatement();
//模拟 SQL 注入，采用拼接字符串的形式
            String sqlQuery = "select * from REPORT where REPORT_ID=" + cmd;
            resultSet = statement.executeQuery(sqlQuery);

        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException sqlException) {
            System.out.println("数据库连接失败");
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        StringEscapeUtils.escapeSql(cmd);
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }
    @PostMapping(value = "case00104/2")
    public Map<String, Object> aTaintCase00104_2(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String driver = "org.sqlite.JDBC";
        String url = "jdbc:sqlite::resource:data/sqlite.db";
        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url);
            if (!con.isClosed()) {
                System.out.println("数据库连接成功");
            }
            statement = con.createStatement();
//模拟 SQL 注入，采用拼接字符串的形式
            String sqlQuery = "select * from REPORT where REPORT_ID=" + cmd;
            resultSet = statement.executeQuery(sqlQuery);

        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动没有安装");
        } catch (SQLException sqlException) {
            System.out.println("数据库连接失败");
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        modelMap.put("status", CommonConsts.SUCCESS_STR);
        return modelMap;
    }
    /**
     * aTaintCase00105 污点无害化处理能力sanitizer->支持自定义unSanitizer(再次污点化)
     */

    /**
     * aTaintCase00106 触发污点跟踪能力（sink）->sink方法特性支持->sink点污点来自固定参数
     */
    @PostMapping(value = "case00106")
    public Map<String, Object> aTaintCase00106(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase00107 触发污点跟踪能力（sink）->sink方法特性支持->sink点污点来自可变参数
     */
    @PostMapping(value = "case00107")
    public Map<String, Object> aTaintCase00107(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            ProcessBuilder processBuilder =new ProcessBuilder("/bin/bash","-c",cmd);
            processBuilder.start();
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase00108 触发污点跟踪能力（sink）->sink方法特性支持->sink点污点来自对象实例
     * path="/data/ls"
     */
    @PostMapping(value = "case00108")
    public Map<String, Object> aTaintCase00108(@RequestParam String path) {
        Map<String, Object> modelMap = new HashMap<>();
        InputStream in = null;
        try {
            in= new FileInputStream(FileUtil.file(path));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return modelMap;
    }
    /**
     * aTaintCase00109 触发污点跟踪能力（sink）->单污点来源传播至多sink点
     */
    @PostMapping(value = "case00109")
    public Map<String, Object> aTaintCase00109(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();

        try {
            Runtime.getRuntime().exec(cmd);
            cmd=cmd+" -la";
            //TODO 加另一个漏洞
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase00110 触发污点跟踪能力（sink）->多污点来源传播至单sink点
     */
    @PostMapping(value = "case00110")
    public Map<String, Object> aTaintCase00110(@RequestParam String cmd1,@RequestParam String cmd2) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd1+" -" +cmd2);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase00111 触发污点跟踪能力（sink）->sink点中嵌套其他sink点
     * 从文件中读取命令，并用命令行执行
     */
    @PostMapping(value = "case00111")
    public Map<String, Object> aTaintCase00111(@RequestParam String path) {
        Map<String, Object> modelMap = new HashMap<>();

        try {
            Runtime.getRuntime().exec(path+ MyCommonTestUtil.readStrFromFile(path));
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }
    /**
     * aTaintCase00112 触发污点跟踪能力（sink）->无污点传播过程，污点直接传入sink
     */
    @PostMapping(value = "case00112")
    public Map<String, Object> aTaintCase00112(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", CommonConsts.SUCCESS_STR);
        } catch (IOException e) {
            modelMap.put("status", CommonConsts.ERROR_STR);
        }
        return modelMap;
    }

}
