package com.alipay.antbenchmark.controller.cases.ModificationAbility.RequestBody;

import org.apache.commons.codec.binary.Base64;
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
import java.io.*;

// assession information start
// real vulnerability = true
// assession project = 改包能力->requestBody->二进制流
// compose = BinaryFlow_01.java
// bind_url = /deserialization/BS00096
// assession information end


@Controller
@RequestMapping(value = "/deserialization")
public class BinaryFlow_01 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BinaryFlow_01");


    @ResponseBody
    @RequestMapping(value = "/BS00096", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            //Base64.decodeBase64(request)
            BufferedReader streamreader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamreader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            String s = new String(Base64.decodeBase64(responseStrBuilder.toString()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(s);
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
            Object obj = ois.readObject();
            response.getWriter().println(obj.toString());
        } catch (Exception e) {
            log.error("doPost error. Exception :{}", e.getMessage());
        }


    }
}
