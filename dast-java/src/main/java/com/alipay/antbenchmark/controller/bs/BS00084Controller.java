package com.alipay.antbenchmark.controller.bs;

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
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/ssrf")
public class BS00084Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00084", method = {RequestMethod.PUT})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        BufferedReader reader = request.getReader();
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        String[] pairs = builder.toString().split("&");
        HashMap<String, String> params = new HashMap<>();
        for (String pair : pairs) {
            String[] parts = pair.split("=");
            String key = parts[0];
            String value = parts.length > 1 ? parts[1] : "";
            params.put(key, value);
        }
        String param = params.get("BS00084");
        param = java.net.URLDecoder.decode(param, StandardCharsets.UTF_8.toString());
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
