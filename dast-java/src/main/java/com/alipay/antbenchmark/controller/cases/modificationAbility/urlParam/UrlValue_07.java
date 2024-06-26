package com.alipay.antbenchmark.controller.cases.modificationAbility.urlParam;

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
// assession project = 改包能力->url参数->value
// compose = UrlValue_07.java
// bind_url = /jsonp/BS00113
// assession information end


@Controller
@RequestMapping(value = "/jsonp")
public class UrlValue_07 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00113", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String param = request.getParameter("BS00113");
        if (param == null) {
            param = "";
        }
        param = param.replace("\n", "").replace("\r", "").replace("\"", "\\\"");
        param = param.replace("<", "").replace(">", "");
        try {
            response.getWriter().println(param + "{\"username\":\"test\"}");
        } catch (Exception e) {
            response.getWriter().println(e.toString());
            return;
        }
    }
}
