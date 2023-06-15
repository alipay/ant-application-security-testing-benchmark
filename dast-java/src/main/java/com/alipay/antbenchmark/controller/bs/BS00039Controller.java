package com.alipay.antbenchmark.controller.bs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/xss")
public class BS00039Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @ResponseBody
    @RequestMapping(value = "/BS00039", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String[] values = request.getParameterValues("BS00039");
        String param;
        if (values != null && values.length > 0) {
            param = values[0];
        } else {
            param = "";
        }
        String bar;
        int num = 86;
        if ((7 * 42) - num > 200) {
            bar = "This_should_always_happen";
        } else {
            bar = param;
        }
        response.setHeader("X-XSS-Protection", "0");
        Object[] obj = {"a", bar};
        java.io.PrintWriter out = response.getWriter();
        out.write("<!DOCTYPE html>\n<html>\n<body>\n<p>");
        out.format(java.util.Locale.US, "Formatted like: %1$s and %2$s.", obj);
        out.write("\n</p>\n</body>\n</html>");
    }

}
