package com.alipay.antbenchmark.controller.cases.modificationAbility.requestBody;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// assession information start
// real vulnerability = false
// bind_url = /ssrf/BS00048
// assession information end


@Controller
@RequestMapping(value = "/ssrf")
public class BodyValue_07 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @ResponseBody
    @RequestMapping(value = "/BS00048", method = {RequestMethod.POST, RequestMethod.GET})
    public String doPost(ModelMap model, HttpServletRequest request) throws ServletException, IOException {
        return (String) request.getParameter("BS00048");
    }


}