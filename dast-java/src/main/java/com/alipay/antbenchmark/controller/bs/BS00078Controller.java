package com.alipay.antbenchmark.controller.bs;

import com.alipay.antbenchmark.tools.Utils;
import com.google.gson.Gson;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Controller
@RequestMapping(value = "/cmdi")
public class BS00078Controller extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BS00078Controller");

    @ResponseBody
    @RequestMapping(value = "/BS00078", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        BufferedReader streamreader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamreader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        Gson gson = new Gson();
        RequestSimple param = gson.fromJson(responseStrBuilder.toString(), RequestSimple.class);
        String bar = param.BS00078;
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
        String[] args = {a1, a2, bar};
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

    public class RequestSimple {
        String username;
        String password;
        String BS00078;
    }
}
