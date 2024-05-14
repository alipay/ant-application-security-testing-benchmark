/*
 * 多层json型SSRF靶场
 * Payload：
 {"rs": {"BS00135":"http://dnslog.com"}}
 * */
package com.alipay.antbenchmark.controller.cases.ModificationAbility.RequestBody;

import com.alipay.antbenchmark.tools.FakeRequestHandler;
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
import java.net.HttpURLConnection;
import java.net.URL;

// assession information start
// real vulnerability = true
// assession project = 改包能力->requestBody->json->value
// compose = BodyJsonValue_10.java
// bind_url = /ssrf/BS00135
// assession information end

@Controller
@RequestMapping(value = "/ssrf")
public class BodyJsonValue_10 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00135", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        BufferedReader streamreader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamreader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        Gson gson = new Gson();
        ParamSimple param = gson.fromJson(responseStrBuilder.toString(), ParamSimple.class);
        if (param.rs.BS00135 == null) {
            param.rs.BS00135 = "";
        }
        FakeRequestHandler scr = new FakeRequestHandler(request);
        StringBuffer responsestr = new StringBuffer();
        try {
            URL url = new URL(param.rs.BS00135);
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

    public class RequestSimple {
        String username;
        String password;
        String BS00135;
    }

    public class ParamSimple {
        String id;
        RequestSimple rs;
    }
}
