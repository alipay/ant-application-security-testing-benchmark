package com.alipay.antbenchmark.controller.cases.responseCheck;

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
import java.util.Map;

// assession information start
// real vulnerability = true
// assession project = 响应检测->响应body识别
// compose = ResponseBodyCheck_03.java
// bind_url = /cmdi/BS00034
// assession information end


@Controller
@RequestMapping(value = "/cmdi")
public class ResponseBodyCheck_04 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("ResponseBodyCheck_04");

    @ResponseBody
    @RequestMapping(value = "/BS00034", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Map<String, String[]> map = request.getParameterMap();
        String param = "";
        if (!map.isEmpty()) {
            String[] values = map.get("BS00034");
            if (values != null) {
                param = values[0];
            }
        }
        String bar;
        int num = 106;
        bar = (7 * 42) - num > 200 ? "This should never happen" : param;
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
            log.error("Problem executing cmdi - java.lang.ProcessBuilder(java.util.List) Test Case");
            throw new ServletException(e);
        }
    }

}
