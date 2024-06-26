package com.alipay.antbenchmark.controller.cases.payload.payloadDeformation;

import com.alipay.antbenchmark.tools.Utils;
import org.apache.commons.codec.binary.Base64;
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
import java.util.Map;

// assession information start
// real vulnerability = true
// assession project = payload-payload变形->追加
// compose = PayloadAppend_02.java
// bind_url = /cmdi/BS00046
// assession information end


@Controller
@RequestMapping(value = "/cmdi")
public class PayloadAppend_02 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("PayloadAppend_02");

    @ResponseBody
    @RequestMapping(value = "/BS00046", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Map<String, String[]> map = request.getParameterMap();
        String param = "";
        if (!map.isEmpty()) {
            String[] values = map.get("BS00046");
            if (values != null) {
                param = values[0];
            }
        }
        String bar = new Test().doSomething(request, param);
        String cmd = Utils.getInsecureOSCommandString(this.getClass().getClassLoader());
        String[] argsEnv = {bar};
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec(cmd, argsEnv);
            Utils.printOSCommandResults(p, response);
        } catch (IOException e) {
            log.error("Problem executing cmdi - TestCase");
            response.getWriter().println(ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    }


    private class Test {

        public String doSomething(HttpServletRequest request, String param) throws ServletException, IOException {

            String bar = "";
            if (param != null) {
                bar = new String(Base64.decodeBase64(
                        Base64.encodeBase64(param.getBytes())));
            }
            return bar;
        }
    }
}
