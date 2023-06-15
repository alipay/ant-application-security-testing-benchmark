package com.alipay.antbenchmark.controller.bs;

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

@Controller
@RequestMapping(value = "/redirect")
public class BS00144Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00144", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = request.getParameter("BS00144");
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
        s += "if (2>3) location.href=x;";
        if (param == null) {
            param = "";
        }
        response.getWriter().write("<script> ");
        response.getWriter().write(s);
        response.getWriter().write("</script> ");

    }
}
