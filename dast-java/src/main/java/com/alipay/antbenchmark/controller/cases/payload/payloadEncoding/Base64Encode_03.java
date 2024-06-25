package com.alipay.antbenchmark.controller.cases.payload.payloadEncoding;

import org.owasp.esapi.codecs.Base64;
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
// assession project = payload->payload编码->base64
// compose = Base64Encode_03.java
// bind_url = /xss/BS00071
// assession information end

@Controller
@RequestMapping(value = "/xss")
public class Base64Encode_03 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00071", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = request.getParameter("BS00071");
        String b64param = new String(Base64.decode(param), "utf-8");
        if (b64param == null) {
            b64param = "";
        }
        response.setHeader("X-XSS-Protection", "0");
        int length = 1;
        if (b64param != null) {
            length = b64param.length();
            response.getWriter().write(b64param, 0, length);
        }

    }
}
