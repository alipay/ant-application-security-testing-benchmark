package com.alipay.antbenchmark.controller.cases.ResponseCheck;

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
import java.util.ArrayList;
import java.util.List;

// assession information start
// real vulnerability = true
// assession project = 响应检测->dnslog
// compose = ResponseDnslogCheck_03.java
// bind_url = /cmdi/BS00115
// assession information end


@Controller
@RequestMapping(value = "/cmdi")
public class ResponseDnslogCheck_03 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("ResponseDnslogCheck_03");

    @ResponseBody
    @RequestMapping(value = "/BS00115", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = request.getParameter("BS00115");
        if (param == null) {
            param = "";
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
        argList.add("echo " + param);
        ProcessBuilder pb = new ProcessBuilder();
        pb.command(argList);
        try {
            Process proc = pb.start();
        } catch (Exception e) {
            log.error("doPost error. Exception :{}", e.getMessage());
        }
    }
}
