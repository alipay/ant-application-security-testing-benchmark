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
public class BS00128Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @ResponseBody
    @RequestMapping(value = "/BS00128", method = {RequestMethod.GET})
    public void naive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        //银行卡泄露
        response.getWriter().println("8880008355523730");
    }


}
