/*
 * 参数=多层json型SSRF靶场
 * Payload：
 BS00137={"rs": {"BS00137":"http://dnslog.com"}}
 * */

package com.alipay.antbenchmark.controller.cases.ModificationAbility.UrlParam;

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
// compose = ValueJson_04.java
// bind_url = /ssrf/BS00137
// assession information end

@Controller
@RequestMapping(value = "/ssrf")
public class ValueJson_04 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("ValueJson_04");

    @ResponseBody
    @RequestMapping(value = "/BS00137", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String inputStr = request.getParameter("BS00137");
            log.info("[*] inputStr:{}" + inputStr);

            Gson gson = new Gson();
            ParamSimple param = gson.fromJson(inputStr, ParamSimple.class);
            log.info("[*] param   :" + param.toString());
            log.info("[*] BS00137 :" + param.rs.BS00137);

            if (param.rs.BS00137 == null) {
                param.rs.BS00137 = "";
            }
            FakeRequestHandler scr = new FakeRequestHandler(request);
            StringBuffer responsestr = new StringBuffer();

            try {
                URL url = new URL(param.rs.BS00137);
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
        String BS00137;
    }

    public class ParamSimple {
        String id;
        RequestSimple rs;
    }
}

