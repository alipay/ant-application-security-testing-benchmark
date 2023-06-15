/*
 * json型SSRF靶场
 * Payload：
 {"BS00134": "http://dnslog.com"}
 * */

package com.alipay.antbenchmark.controller.bs;

import com.alipay.antbenchmark.tools.FakeRequestHandler;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Controller
@RequestMapping(value = "/ssrf")
public class BS00134Controller extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BS00134Controller");


    @ResponseBody
    @RequestMapping(value = "/BS00134", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        BufferedReader streamreader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;

        while ((inputStr = streamreader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        log.info("[*] inputStr:{}", inputStr);
        Gson gson = new Gson();
        RequestSimple param = gson.fromJson(responseStrBuilder.toString(), RequestSimple.class);
        if (param.BS00134 == null) {
            param.BS00134 = "";
        }
        FakeRequestHandler scr = new FakeRequestHandler(request);
        StringBuffer responsestr = new StringBuffer();
        try {
            URL url = new URL(param.BS00134);
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
        String BS00134;
    }
}
