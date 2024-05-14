package com.alipay.antbenchmark.controller.cases.ResponseCheck;

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
// assession project = 响应检测->前端渲染
// compose = ResponseRenderingCheck_03.java
// bind_url = /redirect/BS00108
// assession information end


@Controller
@RequestMapping(value = "/redirect")
public class ResponseRenderingCheck_03 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00108", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = request.getParameter("BS00108");
        if (param == null) {
            param = "";
        }
        param = param.replace("\n", "").replace("\r", "").replace("\"", "\\\"");
        try {
            response.getWriter().println("<script>window.open(\"" + param + "\");</script>");
        } catch (Exception e) {
            response.getWriter().println(e.toString());
            return;
        }
    }
}
