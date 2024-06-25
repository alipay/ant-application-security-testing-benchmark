package com.alipay.antbenchmark.controller.cases.payload.payloadEncoding;

import com.alipay.antbenchmark.tools.Thing;
import com.alipay.antbenchmark.tools.ThingInterface;
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

// assession information start
// real vulnerability = true
// assession project = payload->payload编码->url编码
// compose = UrlEncode_06.java
// bind_url = /cmdi/BS00047
// assession information end


@Controller
@RequestMapping(value = "/cmdi")
public class UrlEncode_06 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("UrlEncode_06");

    @ResponseBody
    @RequestMapping(value = "/BS00047", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String queryString = request.getQueryString();
        String paramval = "BS00047" + "=";
        int paramLoc = -1;
        if (queryString != null) {
            paramLoc = queryString.indexOf(paramval);
        }
        if (paramLoc == -1) {
            response.getWriter().println("getQueryString() couldn't find expected parameter '" + "BenchmarkTest02612" + "' in query string.");
            return;
        }

        String param = queryString.substring(paramLoc + paramval.length());
        int ampersandLoc = queryString.indexOf("&", paramLoc);
        if (ampersandLoc != -1) {
            param = queryString.substring(paramLoc + paramval.length(), ampersandLoc);
        }
        param = URLDecoder.decode(param, "UTF-8");
        String bar = doSomething(param);
        String cmd = Utils.getInsecureOSCommandString(this.getClass().getClassLoader());
        String[] args = {cmd};
        String[] argsEnv = {bar};
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


    private static String doSomething(String param) {

        ThingInterface thing = new Thing();
        String bar = thing.doSomething(param);
        return bar;
    }
}
