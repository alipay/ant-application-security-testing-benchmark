package com.alipay.antbenchmark.controller.cases.ModificationAbility.Header;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// assession information start
// real vulnerability = true
// assession project = 改包能力->header->value
// compose = HeaderValue_02.java
// bind_url = /xss/BS00003
// assession information end

@Controller
@RequestMapping(value = "/xss")
public class HeaderValue_02 extends HttpServlet {

    @ResponseBody
    @RequestMapping(value = "/BS00003", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = "";
        java.util.Enumeration<String> headers = request.getHeaders("Referer");
        if (headers != null && headers.hasMoreElements()) {
            param = headers.nextElement();
        }
        param = java.net.URLDecoder.decode(param, "UTF-8");
        response.setHeader("X-XSS-Protection", "0");
        Object[] obj = {"a", "b"};
        response.getWriter().format(java.util.Locale.US, param, obj);
    }

}
