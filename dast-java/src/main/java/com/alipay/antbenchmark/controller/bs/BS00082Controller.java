package com.alipay.antbenchmark.controller.bs;

import com.alipay.antbenchmark.tools.Utils;
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
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping(value = "/pathtraver")
public class BS00082Controller extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BS00082Controller");

    @ResponseBody
    @RequestMapping(value = "/BS00082", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String fileName = null;
        FileInputStream fis = null;

        String param = request.getParameter("BS00082");
        try {
            fileName = Utils.testfileDir + param + ".html";
            fis = new FileInputStream(new File(fileName));
            byte[] b = new byte[1000];
            int size = fis.read(b);
            response.getWriter().println(new String(b, 0, size));
        } catch (Exception e) {
            log.error("Couldn't open FileInputStream on file: :{}", fileName);
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
}
