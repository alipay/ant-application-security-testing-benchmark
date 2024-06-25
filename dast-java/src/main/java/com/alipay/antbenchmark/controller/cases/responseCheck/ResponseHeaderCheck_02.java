package com.alipay.antbenchmark.controller.cases.responseCheck;

import com.alipay.antbenchmark.tools.FakeRequestHandler;
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
// real vulnerability = false
// bind_url = /redirect/BS00052
// assession information end

@Controller
@RequestMapping(value = "/redirect")
public class ResponseHeaderCheck_02 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00052", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        FakeRequestHandler scr = new FakeRequestHandler(request);
        String param = scr.getFakeValue("BS00052");
        try {
            if (!param.isEmpty()) {
                response.sendRedirect(param);
            }
        } catch (Exception e) {
            response.getWriter().println(e.toString());
            return;
        }
    }
}
