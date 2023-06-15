package com.alipay.antbenchmark.controller.bs;

import com.alibaba.fastjson.JSONObject;
import org.owasp.esapi.ESAPI;
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

import static com.alipay.antbenchmark.tools.Utils.checkFastJsonExploit;
import static com.alipay.antbenchmark.tools.Utils.testfileDir;

@Controller
@RequestMapping(value = "/pathtraver")
public class BS00095Controller extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BS00095Controller");

    @ResponseBody
    @RequestMapping(value = "/BS00095", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // {"command":{"filename":"../../../../etc/passwd","action":"download"}}
        BufferedReader streamreader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamreader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        if (checkFastJsonExploit(responseStrBuilder.toString())) {
            response.getWriter().println("don't hack me");
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
        String param = jsonObject.getJSONObject("command").getString("filename");
        if (param == null) {
            param = "";
        }
        String fileName = null;
        java.io.FileInputStream fis = null;

        try {
            fileName = testfileDir + param;
            fis = new java.io.FileInputStream(new java.io.File(fileName));
            byte[] b = new byte[1000];
            int size = fis.read(b);
            response.getWriter().println("The beginning of file: '" + ESAPI.encoder().encodeForHTML(fileName) + "' is:\n\n" + ESAPI.encoder().encodeForHTML(new String(b, 0, size)));
        } catch (Exception e) {
            log.error("doPost error. Couldn't open FileInputStream on file :{}", fileName);
            response.getWriter().println("Problem getting FileInputStream: " + ESAPI.encoder().encodeForHTML(e.getMessage()));
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


}
