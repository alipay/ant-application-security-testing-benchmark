package com.alipay.antbenchmark.controller.bs;

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

@Controller
@RequestMapping(value = "/xss")
public class BS00156Controller extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BS00156Controller");

    private static String s = "";

    @ResponseBody
    @RequestMapping(value = "/BS00156", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            //普通文本注入
            String text = request.getParameter("BS00156");
            if (text == null) {
                text = "";
            }
            text = text.replace("<", "").replace(">", "");
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
        String BS00156;
    }
}
