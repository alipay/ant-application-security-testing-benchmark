package com.alipay.antbenchmark.controller.cases.ModificationAbility.RequestBody;

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
// real vulnerability = false
// bind_url = /ssrf/BS00141
// assession information end

@Controller
@RequestMapping(value = "/ssrf")
public class BodyValue_15 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00141", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = request.getParameter("BS00141");
        if (!new URL(param).getHost().endsWith(".alipay.com")) {
            response.getWriter().println("Hacker!");
            return;
        }
        StringBuffer responsestr = new StringBuffer();
        String request_url = param;
        try {
            URL url = new URL(request_url);
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
