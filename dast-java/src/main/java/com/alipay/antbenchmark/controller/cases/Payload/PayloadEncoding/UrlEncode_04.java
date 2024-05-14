package com.alipay.antbenchmark.controller.cases.Payload.PayloadEncoding;

import com.alipay.antbenchmark.tools.Utils;
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

// assession information start
// real vulnerability = true
// assession project = payload->payload编码->url编码
// compose = UrlEncode_04.java
// bind_url = /cmdi/BS00041
// assession information end


@Controller
@RequestMapping(value = "/cmdi")
public class UrlEncode_04 extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger("UrlEncode_04");

    @ResponseBody
    @RequestMapping(value = "/BS00041", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String queryString = request.getQueryString();
        String paramval = "BS00041" + "=";
        int paramLoc = -1;
        if (queryString != null) {
            paramLoc = queryString.indexOf(paramval);
        }
        if (paramLoc == -1) {
            response.getWriter().println("getQueryString() couldn't find expected parameter '" + "BenchmarkTest00815" + "' in query string.");
            return;
        }
        String param = queryString.substring(paramLoc + paramval.length());
        int ampersandLoc = queryString.indexOf("&", paramLoc);
        if (ampersandLoc != -1) {
            param = queryString.substring(paramLoc + paramval.length(), ampersandLoc);
        }
        param = URLDecoder.decode(param, "UTF-8");
        String bar = param;
        String a1 = "";
        String a2 = "";
        String osName = System.getProperty("os.name");
        if (osName.indexOf("Windows") != -1) {
            a1 = "cmd.exe";
            a2 = "/c";
        } else {
            a1 = "sh";
            a2 = "-c";
        }
        String[] args = {a1, a2, "echo " + bar};
        ProcessBuilder pb = new ProcessBuilder();
        pb.command(args);
        try {
            Process p = pb.start();
            Utils.printOSCommandResults(p, response);
        } catch (IOException e) {
            log.error("Problem executing cmdi - java.lang.ProcessBuilder(java.util.List) Test Case");
            throw new ServletException(e);
        }
    }

}
