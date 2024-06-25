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
// real vulnerability = false
// bind_url = /sensitive/BS00090
// assession information end

@Controller
@RequestMapping(value = "/sensitive")
public class ResponseBodyCheck_16 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00090", method = {RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String responsestr = "李xx";
        try {
            response.getWriter().println(responsestr);
        } catch (Exception e) {
            response.getWriter().println(e.toString());
            return;
        }

    }
}
