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
import java.util.Map;

// assession information start
// real vulnerability = true
// assession project = 改包能力->url参数->value
// compose = UrlValue_02.java
// bind_url = /xss/BS00008
// assession information end


@Controller
@RequestMapping(value = "/xss")
public class UrlValue_02 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @ResponseBody
    @RequestMapping(value = "/BS00008", method = {RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, String[]> map = request.getParameterMap();
        String param = "";
        if (!map.isEmpty()) {
            String[] values = map.get("BS00008");
            if (values != null) {
                param = values[0];
            }
        }
        response.setHeader("X-XSS-Protection", "0");
        Object[] obj = {"a", "b"};
        response.getWriter().printf(param, obj);
    }
}
