package com.alipay.antbenchmark.controller.cases.responseCheck;

import groovy.lang.GroovyShell;
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
// assession project = 响应检测->响应body识别
// compose = ResponseBodyCheck_09.java
// bind_url = /code_injection/BS00055
// assession information end


@Controller
@RequestMapping(value="/code_injection")
public class ResponseBodyCheck_09 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value="/BS00055",method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = request.getParameter("BS00055");
        GroovyShell groovyShell = new GroovyShell();
        response.getWriter().println(groovyShell.evaluate(param));

    }
}
