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
// assession project = 响应检测->响应header识别
// compose = ResponseHeaderCheck_03.java
// bind_url = /redirect/BS00053
// assession information end


@Controller
@RequestMapping(value = "/redirect")
public class ResponseHeaderCheck_03 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00053", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        java.util.Map<String, String[]> map = request.getParameterMap();
        String param = "";
        if (!map.isEmpty()) {
            String[] values = map.get("BS00053");
            if (values != null) {
                param = values[0];
            }
        }
        try {
            if (!param.isEmpty()) {
                response.sendRedirect(param);
            }
        } catch (Exception e) {
            response.getWriter().println(e.toString());
            return;
        }
    }
}
