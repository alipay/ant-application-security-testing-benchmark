package com.alipay.antbenchmark.controller.cases.modificationAbility.requestMethod;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// assession information start
// real vulnerability = true
// assession project = 改包能力->请求方法->PUT请求
// compose = MethodPut_01.java
// bind_url = /ssrf/BS00084
// assession information end


@Controller
@RequestMapping(value = "/ssrf")
public class MethodPut_01 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00084", method = {RequestMethod.PUT})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = request.getParameter("BS00084");
        StringBuffer responsestr = new StringBuffer();
        if (param.startsWith("https://www.alipay.com")) {
            try {
                URL url = new URL(param);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Moxilla/5.0");
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    responsestr.append(inputLine);
                }
                in.close();
                response.getWriter().println(responsestr.toString());
            } catch (Exception e) {
                response.getWriter().println(e.toString());
                return;
            }
        }
    }
}
