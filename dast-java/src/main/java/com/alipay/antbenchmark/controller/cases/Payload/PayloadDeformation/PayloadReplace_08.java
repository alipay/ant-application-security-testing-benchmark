package com.alipay.antbenchmark.controller.cases.Payload.PayloadDeformation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
// assession project = payload->payload变形->替换参数
// compose = PayloadReplace_08.java
// bind_url = /xss/BS00157
// assession information end

@Controller
@RequestMapping(value = "/xss")
public class PayloadReplace_08 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("PayloadReplace_08");

    private static String s = "";

    @ResponseBody
    @RequestMapping(value = "/BS00157", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            //富文本注入
            String text = request.getParameter("BS00157");
            if (text == null) {
                text = "";
            }
            text = text.toLowerCase();
            text = text.replace("script", "_script_");
            text = text.replace("on", "_on_");

            if (!"".equals(text)) {
                s += "<p>" + text + "</p>";
            }
            response.getWriter().write(s);
        } catch (Exception e) {
            log.error("doPost error. Exception :{}", e.getMessage());
        }

    }

    public class RequestSimple {
        String username;
        String password;
        String BS00157;
    }
}
