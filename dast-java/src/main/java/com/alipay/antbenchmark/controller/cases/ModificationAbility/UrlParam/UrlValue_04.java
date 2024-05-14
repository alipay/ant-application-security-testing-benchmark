package com.alipay.antbenchmark.controller.cases.ModificationAbility.UrlParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


// assession information start
// real vulnerability = true
// assession project = 改包能力->url参数->value
// compose = UrlValue_04.java
// bind_url = /xss/BS00029
// assession information end


@Controller
@RequestMapping(value = "/xss")
public class UrlValue_04 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @ResponseBody
    @RequestMapping(value = "/BS00029", method = {RequestMethod.POST})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = request.getParameter("BS00029");
        if (param == null) {
            param = "";
        }
        String bar = "";
        if (param != null) {
            List<String> valuesList = new ArrayList<String>();
            valuesList.add("safe");
            valuesList.add(param);
            valuesList.add("moresafe");
            valuesList.remove(0);
            bar = valuesList.get(0);
        }
        response.setHeader("X-XSS-Protection", "0");
        Object[] obj = {"a", "b"};
        response.getWriter().printf(Locale.US, bar, obj);
    }

}
