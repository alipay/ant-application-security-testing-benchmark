package com.alipay.antbenchmark.controller.cases.payload.payloadDeformation;

import com.alipay.antbenchmark.tools.Utils;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

// assession information start
// real vulnerability = false
// bind_url = /pathtraver/BS00031
// assession information end


@Controller
@RequestMapping(value = "/pathtraver")
public class SymbolAdd_02 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("SymbolAdd_02");

    @ResponseBody
    @RequestMapping(value = "/BS00031", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, String[]> map = request.getParameterMap();
        String param = "";
        if (!map.isEmpty()) {
            String[] values = map.get("BS00031");
            if (values != null) {
                param = values[0];
            }
        }
        String bar;
        String guess = "ABC";
        char switchTarget = guess.charAt(1);
        switch (switchTarget) {
            case 'A':
                bar = param;
                break;
            case 'B':
                bar = "bob";
                break;
            case 'C':
            case 'D':
                bar = param;
                break;
            default:
                bar = "bob's your uncle";
                break;
        }

        String fileName = null;
        FileOutputStream fos = null;
        try {
            fileName = Utils.testfileDir + bar;
            fos = new FileOutputStream(new File(fileName));
            response.getWriter().println("Now ready to write to file: " + ESAPI.encoder().encodeForHTML(fileName));
        } catch (Exception e) {
            log.error("Couldn't open FileOutputStream on file:{} , Exception:{}", fileName ,e.getMessage());
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    log.error("doPost error. Exception :{}", e.getMessage());
                }
            }
        }
    }

}
