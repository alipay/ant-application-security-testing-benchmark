package com.alipay.antbenchmark.controller.cases.responseCheck;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
// compose = ResponseRenderingCheck_05.java
// bind_url = /redirect/BS00143
// assession information end


@Controller
@RequestMapping(value = "/redirect")
public class ResponseRenderingCheck_05 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00143", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = request.getParameter("BS00143");
        if (param == null) {
            param = "";
        }
        param = StringUtils.replace(param, "&", "&amp;");
        param = StringUtils.replace(param, "<", "&lt;");
        param = StringUtils.replace(param, ">", "&gt;");
        param = StringUtils.replace(param, "\"", "&quot;");
        param = StringUtils.replace(param, "'", "&#x27;");

        String s = "var x='" + param + "';\n";
        s += "for(var i=0;i<10;i++);{}\n";
        s += "location.href=x;";
        if (param == null) {
            param = "";
        }
        response.getWriter().write("<script> ");
        response.getWriter().write(s);
        response.getWriter().write("</script> ");

    }
}
