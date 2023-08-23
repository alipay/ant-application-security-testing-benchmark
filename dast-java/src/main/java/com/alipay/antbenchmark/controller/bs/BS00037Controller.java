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
import java.util.Map;

@Controller
@RequestMapping(value = "/cmdi")
public class BS00037Controller extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger("BS00037Controller");

    @ResponseBody
    @RequestMapping(value = "/BS00037", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, String[]> map = request.getParameterMap();
        String param = "";
        if (!map.isEmpty()) {
            String[] values = map.get("BS00037");
            if (values != null) {
                param = values[0];
            }
        }
        String bar;
        int num = 196;
        if ((500 / 42) + num > 200) {
            bar = param;
        } else {
            bar = "This should never happen";
        }
        String cmd = "";
        String osName = System.getProperty("os.name");
        if (osName.indexOf("Windows") != -1) {
            cmd = Utils.getOSCommandString("echo");
        }
        String[] argsEnv = {"Foo=bar"};
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec(cmd + bar, argsEnv, new File(System.getProperty("user.dir")));
            Utils.printOSCommandResults(p, response);
        } catch (IOException e) {
            log.error("Problem executing cmdi - TestCase");
            response.getWriter().println(ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    }

}
