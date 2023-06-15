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
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/cmdi")
public class BS00025Controller extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BS00025Controller");

    @ResponseBody
    @RequestMapping(value = "/BS00025", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String param = "";
            if (request.getHeader("BS00025") != null) {
                param = request.getHeader("BS00025");
            }
            param = URLDecoder.decode(param, "UTF-8");

            String bar = "safe!";
            HashMap<String, Object> map68097 = new HashMap<String, Object>();
            map68097.put("keyA-68097", "a-Value");
            map68097.put("keyB-68097", param);
            map68097.put("keyC", "another-Value");
            bar = (String) map68097.get("keyB-68097");

            String cmd = Utils.getInsecureOSCommandString(this.getClass().getClassLoader());
            bar = bar.toLowerCase();
            bar = bar.replace("foo", "FOO");
            String[] argsEnv = {bar};
            Runtime r = Runtime.getRuntime();
            try {
                if (param.toUpperCase().equals(param)) {
                    log.info("env:{}" + bar);
                    Process p = r.exec(cmd, argsEnv);
                    Utils.printOSCommandResults(p, response);
                }
            } catch (IOException e) {
                log.error("Problem executing cmdi - TestCase");
                response.getWriter().println(ESAPI.encoder().encodeForHTML(e.getMessage()));
                return;
            }
        } catch (Exception e) {
            log.error("doPost error. Exception :{}", e.getMessage());
        }
    }

}
