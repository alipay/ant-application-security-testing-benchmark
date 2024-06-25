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
// compose = ResponseBodyCheck_05.java
// bind_url = /cmdi/BS00035
// assession information end

@Controller
@RequestMapping(value = "/cmdi")
public class ResponseBodyCheck_05 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("ResponseBodyCheck_05");

    @ResponseBody
    @RequestMapping(value = "/BS00035", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, String[]> map = request.getParameterMap();
        String param = "";
        if (!map.isEmpty()) {
            String[] values = map.get("BS00035");
            if (values != null) {
                param = values[0];
            }
        }
        String bar = "";
        if (param != null) {
            List<String> valuesList = new ArrayList<String>();
            valuesList.add("safe");
            valuesList.add(param);
            valuesList.add("moresafe");
            valuesList.remove(0);
            bar = valuesList.get(0);
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
            response.getWriter().println(org.owasp.esapi.ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    }

}
