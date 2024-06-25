package com.alipay.antbenchmark.controller.cases.payload.payloadEncoding;

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
// assession project = payload->payload编码->url编码
// compose = UrlEncode_03.java
// bind_url = /xss/BS00017
// assession information end



@Controller
@RequestMapping(value = "/xss")
public class UrlEncode_03 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @ResponseBody
    @RequestMapping(value = "/BS00017", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String queryString = request.getQueryString();
        String paramval = "BS00017" + "=";
        int paramLoc = -1;
        if (queryString != null){
            paramLoc = queryString.indexOf(paramval);
        }
        if (paramLoc == -1) {
            response.getWriter().println("getQueryString() couldn't find expected parameter '" + "BenchmarkTest00049" + "' in query string.");
            return;
        }

        String param = queryString.substring(paramLoc + paramval.length());
        int ampersandLoc = queryString.indexOf("&", paramLoc);
        if (ampersandLoc != -1) {
            param = queryString.substring(paramLoc + paramval.length(), ampersandLoc);
        }
        param = URLDecoder.decode(param, "UTF-8");
        response.setHeader("X-XSS-Protection", "0");
        response.getWriter().write("Parameter value: " + param);
    }
}
