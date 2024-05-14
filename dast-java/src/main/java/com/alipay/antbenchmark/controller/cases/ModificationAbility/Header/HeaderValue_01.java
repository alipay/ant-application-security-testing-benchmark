package com.alipay.antbenchmark.controller.cases.ModificationAbility.Header;

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
import java.util.ArrayList;
import java.util.List;

// assession information start
// real vulnerability = true
// assession project = 改包能力->header->value
// compose = HeaderValue.java
// bind_url = /cmdi/BS00002
// assession information end
/**
 * @date: 2023/4/23
 */
@Controller
@RequestMapping(value = "/cmdi")
public class HeaderValue_01 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("HeaderValue_01");


    @ResponseBody
    @RequestMapping(value = "/BS00002", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = "";
        if (request.getHeader("BS00002") != null) {
            param = request.getHeader("BS00002");
        }
        param = java.net.URLDecoder.decode(param, "UTF-8");
        List<String> argList = new ArrayList<>();
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
            Process p = pb.start();
            Utils.printOSCommandResults(p, response);
        } catch (IOException e) {
            log.error("Problem executing cmdi - java.lang.ProcessBuilder(java.util.List) Test Case . the exception:{}", e.getMessage());
        }
    }
}
