package com.alipay.antbenchmark.controller.cases.Payload.PayloadDeformation;

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
import java.util.HashMap;

// assession information start
// real vulnerability = true
// assession project = payload-payload变形->追加
// compose = PayloadAppend_01.java
// bind_url = /cmdi/BS00040
// assession information end


@Controller
@RequestMapping(value = "/cmdi")
public class PayloadAppend_01 extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger("PayloadAppend_01");

    @ResponseBody
    @RequestMapping(value = "/BS00040", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String[] values = request.getParameterValues("BS00040");
        String param;
        if (values != null && values.length > 0) {
            param = values[0];
        } else {
            param = "";
        }
        String bar = "safe!";
        HashMap<String, Object> map87432 = new HashMap<String, Object>();
        map87432.put("keyA-87432", "a-Value");
        map87432.put("keyB-87432", param);
        map87432.put("keyC", "another-Value");
        bar = (String) map87432.get("keyB-87432");
        String cmd = Utils.getInsecureOSCommandString(this.getClass().getClassLoader());
        String[] args = {cmd};
        String[] argsEnv = {bar};
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec(args, argsEnv);
            Utils.printOSCommandResults(p, response);
        } catch (IOException e) {
            log.error("Problem executing cmdi - TestCase");
            response.getWriter().println(ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    }

}
