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
public class BS00153Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00153", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // some code
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form action=\"login\">\n" +
                "        username: <input type=\"text\" name=\"username\" value=\"admin\"><br>\n" +
                "        password: <input type=\"password\" name=\"password\" value=\"secretpassword\"><br>\n" +
                "        <input type=\"submit\" value=\"提交\">\n" +
                "      </form>\n" +
                "      \n" +
                "</body>\n" +
                "</html>");

    }

}
