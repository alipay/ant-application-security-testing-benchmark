package com.alipay.antbenchmark.controller.cases.ResponseCheck;

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
// assession project = 响应检测->响应body识别
// compose = ResponseBodyCheck_06.java
// bind_url = /cmdi/BS00036
// assession information end


@Controller
@RequestMapping(value = "/cmdi")
public class ResponseBodyCheck_06 extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger("ResponseBodyCheck_06");

    @ResponseBody
    @RequestMapping(value = "/BS00036", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, String[]> map = request.getParameterMap();
        String param = "";
        if (!map.isEmpty()) {
            String[] values = map.get("BS00036");
            if (values != null) {
                param = values[0];
            }
        }
        String bar = "";
        if (param != null) {
            bar = new String(Base64.decodeBase64(Base64.encodeBase64(param.getBytes())));
        }
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
