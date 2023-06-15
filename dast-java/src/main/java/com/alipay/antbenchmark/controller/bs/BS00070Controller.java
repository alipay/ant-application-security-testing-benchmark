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
@RequestMapping(value = "/sensitive")
public class BS00070Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00070", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String responsestr = "";
        for (int i = 0; i < 18; i++) {
            responsestr += String.valueOf(Math.random() * 10).toCharArray()[0];
        }
        try {
            response.getWriter().println(responsestr);
        } catch (Exception e) {
            response.getWriter().println(e.toString());
            return;
        }
    }
}
