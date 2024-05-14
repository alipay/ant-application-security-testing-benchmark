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
// compose = ResponseBodyCheck_22.java
// bind_url = /sensitive/BS00105
// assession information end


@Controller
@RequestMapping(value = "/sensitive")
public class ResponseBodyCheck_22 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00105", method = {RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = request.getParameter("BS00105");
        Integer id = Integer.parseInt(param);

        String[] firstnames = new String[]{"罗", "梁", "宋", "唐", "许", "韩", "冯", "邓", "曹", "彭", "曾", "萧", "田", "董"};
        String[] secondnames = new String[]{"伟", "芳", "娜", "敏", "静", "秀英", "浩然", "子轩", "梓萱", "梓涵"};
        try {
            String name = firstnames[id % (firstnames.length - 1)] + secondnames[id % (secondnames.length - 1)];
            String phone = "1390395" + String.format("%04d", id % 10000);
            response.getWriter().println(
                    "{\"name\": \"nameformat\", \"phone\": \"phoneformat\"}".replace("nameformat", name).replace("phoneformat", phone)
            );
        } catch (Exception e) {
            response.getWriter().println(e.toString());
            return;
        }

    }
}
