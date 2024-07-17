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
// real vulnerability = true
// assession project = 响应检测->前端渲染
// compose = ResponseRenderingCheck_04.java
// bind_url = /sensitive/BS00116
// assession information end


@Controller
@RequestMapping(value = "/sensitive")
public class ResponseRenderingCheck_04 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @ResponseBody
    @RequestMapping(value = "/BS00116", method = {RequestMethod.GET})
    public void naive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //response.setContentType("Content-Type: text/javascript; charset=UTF-8");
        //ak泄露
        response.getWriter().println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Naive</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <script src=\"script.js\"></script>\n" +
                "</body>\n" +
                "</html>");
    }

    @ResponseBody
    @RequestMapping(value = "/script.js", method = {RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("Content-Type: text/javascript; charset=UTF-8");
        //ak泄露

        try {
            response.getWriter().println("{\"ALIYUN_ACCESSKEYSECRET\": \"iNk6rAbsNA7bznWSCw7aR23W2kAN8a\", \"ALIYUN_ACCESSKEYID\": \"LTAILQbK6EKTgNfk\"}");
        } catch (Exception e) {
            response.getWriter().println(e.toString());
            return;
        }

    }
}
