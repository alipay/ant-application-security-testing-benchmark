package com.alipay.antbenchmark.controller.cases.modificationAbility.urlParam;

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

// assession information start
// real vulnerability = true
// assession project = 改包能力->url参数->value->value中的json
// compose = ValueJson_03.java
// bind_url = /ssrf/BS00136
// assession information end

@Controller
@RequestMapping(value = "/ssrf")
public class ValueJson_03 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("ValueJson_03");


    @ResponseBody
    @RequestMapping(value = "/BS00136", method = {RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String inputStr = request.getParameter("BS00136");
            log.info("[*] inputStr:{}" + inputStr);

            Gson gson = new Gson();
            RequestSimple param = gson.fromJson(inputStr, RequestSimple.class);
            log.info("[*] param   :{}" + param.toString());
            log.info("[*] BS00136 :{}" + param.BS00136);

            if (param.BS00136 == null) {
                param.BS00136 = "";
            }
            FakeRequestHandler scr = new FakeRequestHandler(request);
            StringBuffer responsestr = new StringBuffer();
            try {
                URL url = new URL(param.BS00136);
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
        } catch (Exception e) {
            log.error("doPost error. Exception :{}", e.getMessage());
        }
    }

    public class RequestSimple {
        String username;
        String password;
        String BS00136;
    }
}

