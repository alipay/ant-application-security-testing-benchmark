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
import java.sql.Connection;
import java.sql.DriverManager;


@Controller
@RequestMapping(value = "/ssrf")
public class BS00109Controller extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BS00109Controller");


    @ResponseBody
    @RequestMapping(value = "/BS00109", method = {RequestMethod.GET, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //jdbc ssrf
        String param = request.getParameter("BS00109");
        if (param == null) {
            param = "";
        }
        String username = request.getParameter("username");
        if (username == null) {
            username = "";
        }
        String password = request.getParameter("password");
        if (password == null) {
            password = "";
        }
        try {
            String url = param;
            DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            log.error("doPost error. Exception :{}", e.getMessage());
        }
    }
}
