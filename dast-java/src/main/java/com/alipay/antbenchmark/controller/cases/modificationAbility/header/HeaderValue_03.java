package com.alipay.antbenchmark.controller.cases.modificationAbility.header;

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
// compose = HeaderValue_03.java
// bind_url = /xss/BS00004
// assession information end


@Controller
@RequestMapping(value = "/xss")
public class HeaderValue_03 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @ResponseBody
    @RequestMapping(value = "/BS00004", method = {RequestMethod.POST, RequestMethod.GET})
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
        response.getWriter().format(param, obj);
    }
}
