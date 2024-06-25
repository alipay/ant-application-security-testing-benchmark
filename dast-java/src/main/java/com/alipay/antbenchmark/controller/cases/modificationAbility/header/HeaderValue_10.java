package com.alipay.antbenchmark.controller.cases.modificationAbility.header;

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
import java.net.URLDecoder;

// assession information start
// real vulnerability = true
// assession project = 改包能力->header->value
// compose = HeaderValue_10.java
// bind_url = /cmdi/BS00045
// assession information end


@Controller
@RequestMapping(value = "/cmdi")
public class HeaderValue_10 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("HeaderValue_10");

    @ResponseBody
    @RequestMapping(value = "/BS00045", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = "";
        if (request.getHeader("BS00045") != null) {
            param = request.getHeader("BS00045");
        }
        param = URLDecoder.decode(param, "UTF-8");
        String bar = new Test().doSomething(request, param);
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
