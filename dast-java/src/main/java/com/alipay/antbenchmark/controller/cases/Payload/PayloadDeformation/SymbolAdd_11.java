package com.alipay.antbenchmark.controller.cases.Payload.PayloadDeformation;

import com.alibaba.fastjson.JSONObject;
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

// assession information start
// real vulnerability = true
// assession project = payload->payload变形->payload前后增加非字母符号
// compose = SymbolAdd_11.java
// bind_url = /deserialization/BS00125
// assession information end


@Controller
@RequestMapping(value = "/deserialization")
public class SymbolAdd_11 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("SymbolAdd_11");

    @ResponseBody
    @RequestMapping(value = "/BS00125", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = null;
        try {
            BufferedReader streamreader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamreader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            //大括号闭合能力检测
            JSONObject jsonObject = JSONObject.parseObject("{" + responseStrBuilder.toString());
            param = jsonObject.toJSONString();
            response.getWriter().println(param);
        } catch (Exception e) {
            log.error("doPost error. Exception :{}", e.getMessage());
        }

    }
}
