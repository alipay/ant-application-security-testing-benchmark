package com.alipay.antbenchmark.controller.cases.ModificationAbility.RequestBody;
import com.google.gson.Gson;
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

// assession information start
// real vulnerability = true
// assession project = 改包能力->requestBody->json->value
// compose = BodyJsonValue_04.java
// bind_url = /xss/BS00072
// assession information end


@Controller
@RequestMapping(value = "/xss")
public class BodyJsonValue_04 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00072", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            BufferedReader streamreader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamreader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            Gson gson = new Gson();
            RequestSimple param = gson.fromJson(responseStrBuilder.toString(), RequestSimple.class);
            if (param.BS00072 == null) {
                param.BS00072 = "";
            }
            response.setHeader("X-XSS-Protection", "0");
            int length = 1;
            if (param.BS00072 != null) {
                length = param.BS00072.length();
                response.getWriter().write(param.BS00072, 0, length);
            }
        } catch (Exception e) {
            response.getWriter().write(e.toString());
        }
    }

    public class RequestSimple {
        String username;
        String password;
        String BS00072;
    }
}
