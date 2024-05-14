package com.alipay.antbenchmark.controller.cases.ModificationAbility.UrlParam;

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
// assession project = 改包能力->url参数->key
// compose = UrlKey_01.java
// bind_url = /xss/BS00013
// assession information end


@Controller
@RequestMapping(value = "/xss")
public class UrlKey_01 extends HttpServlet {

    @ResponseBody
    @RequestMapping(value = "/BS00013", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = "";
        boolean flag = true;
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements() && flag) {
            String name = names.nextElement();
            String[] values = request.getParameterValues(name);
            if (values != null) {
                for (int i = 0; i < values.length && flag; i++) {
                    String value = values[i];
                    if ("BS00013".equals(value)) {
                        param = name;
                        flag = false;
                    }
                }
            }
        }
        response.setHeader("X-XSS-Protection", "0");
        int length = 1;
        if (param != null) {
            length = param.length();
            response.getWriter().write(param.toCharArray(), 0, length);
        }
    }
}
