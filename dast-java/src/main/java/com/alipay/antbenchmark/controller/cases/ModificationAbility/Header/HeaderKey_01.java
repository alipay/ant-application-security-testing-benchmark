package com.alipay.antbenchmark.controller.cases.ModificationAbility.Header;

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
import java.util.Enumeration;

// assession information start
// real vulnerability = true
// assession project = 改包能力->header->value
// compose = HeaderKey_01.java
// bind_url = /cmdi/BS00038
// assession information end


@Controller
@RequestMapping(value = "/cmdi")
public class HeaderKey_01 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("HeaderKey_01");

    @ResponseBody
    @RequestMapping(value = "/BS00038", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = "";
        boolean flag = true;
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements() && flag) {
            String name = (String) names.nextElement();
            String[] values = request.getParameterValues(name);
            if (values != null) {
                for (int i = 0; i < values.length && flag; i++) {
                    String value = values[i];
                    if ("BS00038".equals(value)) {
                        param = name;
                        flag = false;
                    }
                }
            }
        }
        String bar = "";
        if (param != null) {
            bar = new String(Base64.decodeBase64(Base64.encodeBase64(param.getBytes())));
        }
        String cmd = "";
        String osName = System.getProperty("os.name");
        if (osName.indexOf("Windows") != -1) {
            cmd = Utils.getOSCommandString("echo");
        }
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec(cmd + bar);
            Utils.printOSCommandResults(p, response);
        } catch (IOException e) {
            log.error("Problem executing cmdi - TestCase");
            response.getWriter().println(ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    }

}
