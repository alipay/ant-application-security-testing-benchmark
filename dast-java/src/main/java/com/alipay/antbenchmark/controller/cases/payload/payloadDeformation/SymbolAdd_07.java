package com.alipay.antbenchmark.controller.cases.payload.payloadDeformation;

import com.alipay.antbenchmark.tools.Utils;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

// assession information start
// real vulnerability = true
// assession project = payload->payload变形->payload前后增加非字母符号
// compose = SymbolAdd_07.java
// bind_url = /cmdi/BS00121
// assession information end


@Controller
@RequestMapping(value="/cmdi")
public class SymbolAdd_07 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("SymbolAdd_07");

    @ResponseBody
    @RequestMapping(value="/BS00121",method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param=request.getParameter("BS00121");
        if(param==null){
            param="";
        }
        //检测分号变形能力
        if(param.contains("&") || param.contains("|")||param.contains("`")){
            response.getWriter().write("不能有&或者|或者`");
        }
        param = URLDecoder.decode(param, "UTF-8");
        String bar = "";
        if (param != null) {
            bar = new String( Base64.decodeBase64( Base64.encodeBase64( param.getBytes() ) ));
        }
        List<String> argList = new ArrayList<String>();
        String osName = System.getProperty("os.name");
        if (osName.indexOf("Windows") != -1) {
            argList.add("cmd.exe");
            argList.add("/c");
        } else {
            argList.add("sh");
            argList.add("-c");
        }
        argList.add("echo " + bar);
        ProcessBuilder pb = new ProcessBuilder();
        pb.command(argList);
        try {
            Process p = pb.start();
            Utils.printOSCommandResults(p, response);
        } catch (IOException e) {
            log.error("doPost error. Problem executing cmdi - java.lang.ProcessBuilder(java.util.List) Test Case .IOException :{}", e.getMessage());
            throw new ServletException(e);
        }
    }

}
