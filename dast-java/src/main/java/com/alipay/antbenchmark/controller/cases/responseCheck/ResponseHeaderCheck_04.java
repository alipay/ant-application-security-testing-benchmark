package com.alipay.antbenchmark.controller.cases.responseCheck;

import com.alipay.antbenchmark.tools.FakeRequestHandler;
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
// assession project = 响应检测->响应header识别
// compose = ResponseHeaderCheck_04.java
// bind_url = /cors/BS00111
// assession information end

@Controller
@RequestMapping(value = "/cors")
public class ResponseHeaderCheck_04 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00111", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        FakeRequestHandler scr = new FakeRequestHandler(request);
        try {
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        } catch (Exception e) {
            response.getWriter().println(e.toString());
            return;
        }
    }
}
