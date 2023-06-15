package com.alipay.antbenchmark.controller.bs;

import com.alipay.antbenchmark.tools.Utils;
import org.owasp.esapi.ESAPI;
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
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/cmdi")
public class BS00024Controller extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BS00024Controller");

    @ResponseBody
    @RequestMapping(value = "/BS00024", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = "";
        if (request.getHeader("BS00024") != null) {
            param = request.getHeader("BS00024");
        }
        param = URLDecoder.decode(param, "UTF-8");

        String bar = "safe!";
        HashMap<String, Object> map40534 = new HashMap<String, Object>();
        map40534.put("keyA-40534", "a_Value");
        map40534.put("keyB-40534", param);
        map40534.put("keyC", "another_Value");
        bar = (String) map40534.get("keyB-40534");
        bar = (String) map40534.get("keyA-40534");

        String cmd = "";
        String a1 = "";
        String a2 = "";
        String[] args = null;
        String osName = System.getProperty("os.name");
        if (osName.indexOf("Windows") != -1) {
            a1 = "cmd.exe";
            a2 = "/c";
            cmd = "echo ";
            args = new String[]{a1, a2, cmd, bar};
        } else {
            a1 = "sh";
            a2 = "-c";
            cmd = Utils.getOSCommandString("ls ");
            args = new String[]{a1, a2, cmd + bar};
        }
        String[] argsEnv = {"foo=bar"};
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec(args, argsEnv, new File(System.getProperty("user.dir")));
            Utils.printOSCommandResults(p, response);
        } catch (IOException e) {
            log.error("Problem executing cmdi - TestCase");
            response.getWriter().println(ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    }

}
