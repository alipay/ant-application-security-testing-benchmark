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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/cmdi")
public class BS00042Controller extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger("BS00042Controller");

    @ResponseBody
    @RequestMapping(value = "/BS00042", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String queryString = request.getQueryString();
        String paramval = "BS00042" + "=";
        int paramLoc = -1;
        if (queryString != null) {
            paramLoc = queryString.indexOf(paramval);
        }
        if (paramLoc == -1) {
            response.getWriter().println("getQueryString() couldn't find expected parameter '" + "BenchmarkTest00823" + "' in query string.");
            return;
        }
        String param = queryString.substring(paramLoc + paramval.length());
        int ampersandLoc = queryString.indexOf("&", paramLoc);
        if (ampersandLoc != -1) {
            param = queryString.substring(paramLoc + paramval.length(), ampersandLoc);
        }
        param = URLDecoder.decode(param, "UTF-8");
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
            response.getWriter().println(ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    }
}
