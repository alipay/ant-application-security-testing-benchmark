package com.alipay.antbenchmark.controller.cases.responseCheck;

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
// assession project = 响应检测->状态码识别
// compose = ResponseStatusCheck_02.java
// bind_url = /redirect/BS00106
// assession information end


@Controller
@RequestMapping(value = "/redirect")
public class ResponseStatusCheck_02 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00106", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = request.getParameter("BS00106");
        if (param == null) {
            param = "";
        }
        try {
            response.setStatus(301);
            //不加这个空格会出现奇妙的错误
            //unsport redirect location:什么的
            response.setHeader("Location ", param);
            response.setHeader("Connection", "close");
        } catch (Exception e) {
            response.getWriter().println(e.toString());
            return;
        }
    }
}
