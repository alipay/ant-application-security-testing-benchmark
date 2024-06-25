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
// compose = ResponseRenderingCheck_01.java
// bind_url = /redirect/BS00054
// assession information end


@Controller
@RequestMapping(value = "/redirect")
public class ResponseRenderingCheck_01 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00054", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        java.util.Map<String, String[]> map = request.getParameterMap();
        String param = "";
        if (!map.isEmpty()) {
            String[] values = map.get("BS00054");
            if (values != null) {
                param = values[0];
            }
        }
        try {
            if (!param.isEmpty()) {
                response.getWriter().println("<html><script language=\"javascript\" type=\"text/javascript\">window.location.href=\"" + param + "\"</script></html>");
            }
        } catch (Exception e) {
            response.getWriter().println(e.toString());
            return;
        }
    }
}
