package com.alipay.antbenchmark.controller.cases.ModificationAbility.RequestBody;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
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
// assession project = 改包能力->requestBody->xml->标签值
// compose = BodyXmlValue_04.java
// bind_url = /xss/BS00074
// assession information end


@Controller
@RequestMapping(value = "/xss")
public class BodyXmlValue_04 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00074", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            BufferedReader streamreader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            String inputStr = null;
            StringBuffer xml = new StringBuffer();
            while ((inputStr = streamreader.readLine()) != null) {
                xml.append(inputStr);
            }
            String callbackMessage = xml.toString();
            String param = null;
            int length = 1;
            if (!StringUtils.isEmpty(callbackMessage)) {
                Document doc = null;
                doc = DocumentHelper.parseText(callbackMessage);
                Element rootElt = doc.getRootElement();
//                String username = rootElt.element("username").getTextTrim();
//                String password = rootElt.element("password").getTextTrim();
                param = rootElt.element("BS00074").getTextTrim();

            }
            if (param != null) {
                length = param.length();
                response.getWriter().write(param, 0, length);
            }
        } catch (Exception e) {
            response.getWriter().write(e.toString());
        }
    }

    public class RequestSimple {
        String username;
        String password;
        String BS00074;
    }
}
