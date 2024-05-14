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
// real vulnerability = false
// bind_url = /sensitive/BS00127
// assession information end


@Controller
@RequestMapping(value = "/sensitive")
public class ResponseBodyCheck_25 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00127", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<script src=\"//ossweb-img.dnslog.com/images/js/jquery/jquery-1.9.1.min.js\"></script> \n");

    }
}
