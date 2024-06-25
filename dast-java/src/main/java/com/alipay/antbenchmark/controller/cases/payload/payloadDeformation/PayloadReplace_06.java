package com.alipay.antbenchmark.controller.cases.payload.payloadDeformation;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
// assession project = payload->payload变形->替换参数
// compose = PayloadReplace_06.java
// bind_url = /xss/BS00155
// assession information end


@Controller
@RequestMapping(value="/xss")
public class PayloadReplace_06 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("PayloadReplace_06");


    @ResponseBody
    @RequestMapping(value="/BS00155",method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            BufferedReader streamreader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
            String inputStr = null;
            StringBuffer xml = new StringBuffer();
            while ((inputStr = streamreader.readLine()) != null) {
                xml.append(inputStr);
            }
            String callbackMessage = xml.toString();
            String param = null;
            if(!StringUtils.isEmpty(callbackMessage)){
                Document doc = null;
                doc = DocumentHelper.parseText(callbackMessage);
                Element rootElt = doc.getRootElement();
                param = rootElt.element("BS00155").element("BS00155").getTextTrim();
            }
            if (param == null) {
                param = "";
            }
            response.getWriter().write(param);
        } catch (Exception e){
            log.error("doPost error. Exception :{}", e.getMessage());
        }

    }

    public class RequestSimple{
        String username;
        String password;
        String BS00155;
    }
}
