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
// assession project = 响应检测->响应body识别
// compose = ResponseBodyCheck_21.java
// bind_url = /sensitive/BS00104
// assession information end

@Controller
@RequestMapping(value = "/sensitive")
public class ResponseBodyCheck_21 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00104", method = {RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //ak泄露
        try {
            response.getWriter().println("{\"ALIYUN_ACCESSKEYSECRET\": \"iTk1rAbsNa7bznWSCw7aR23W2kAN1a\", \"ALIYUN_ACCESSKEYID\": \"LTAI2FdesjE4ETjJvVmfNdyN\"}");
        } catch (Exception e) {
            response.getWriter().println(e.toString());
            return;
        }

    }
}
