package com.alipay.antbenchmark.controller.bs;

import com.alipay.antbenchmark.tools.Utils;
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

@Controller
@RequestMapping(value = "/pathtraver")
public class BS00080Controller extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BS00080Controller");

    @ResponseBody
    @RequestMapping(value = "/BS00080", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        BufferedReader streamreader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamreader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        Gson gson = new Gson();
        RequestSimple param = gson.fromJson(responseStrBuilder.toString(), RequestSimple.class);
        String bar = param.BS00080;
        String fileName = null;
        java.io.FileInputStream fis = null;
        try {
            fileName = Utils.testfileDir + bar;
            fis = new java.io.FileInputStream(new java.io.File(fileName));
            byte[] b = new byte[1000];
            int size = fis.read(b);
            response.getWriter().println(new String(b, 0, size));
        } catch (Exception e) {
            log.error("Couldn't open FileInputStream on file: {}", fileName);
            response.getWriter().println("Problem getting FileInputStream: " + org.owasp.esapi.ESAPI.encoder().encodeForHTML(e.getMessage()));
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    log.error("doPost error. Exception :{}", e.getMessage());
                }
            }
        }
    }

    public class RequestSimple {
        String username;
        String password;
        String BS00080;
    }
}
