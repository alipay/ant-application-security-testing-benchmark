package com.alipay.antbenchmark.controller.cases.modificationAbility.requestBody;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


// assession information start
// real vulnerability = true
// assession project = 改包能力->requestBody->key=value->value
// compose = BodyValue_13.java
// bind_url = /ssrf/BS00138
// assession information end


@Controller
@RequestMapping(value = "/ssrf")
public class BodyValue_13 {
    @RequestMapping(value = "/BS00138", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String url = request.getParameter("BS00138");
            url = url.toLowerCase();
            //file协议限定的SSRF
            if (url.startsWith("file://")) {
                response.getWriter().println("not file protocol!");
            }
            URL u = new URL(url);
            URLConnection urlConnection = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuffer responsestr = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                responsestr.append(inputLine);
            }
            in.close();
            response.getWriter().println(responsestr.toString());
        } catch (Exception e) {
            response.getWriter().println(e.toString());
        }
    }
}

