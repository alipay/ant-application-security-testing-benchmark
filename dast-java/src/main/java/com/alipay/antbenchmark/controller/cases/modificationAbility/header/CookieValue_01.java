package com.alipay.antbenchmark.controller.cases.modificationAbility.header;


import static com.alipay.antbenchmark.tools.Utils.testfileDir;

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
import java.io.IOException;

// assession information start
// real vulnerability = true
// assession project = 改包能力->header->cookie中的value
// compose = CookieValue_01.java
// bind_url = /pathtraver/BS00001
// assession information end


/**
 * @date: 2023/4/23
 */
@Controller
@RequestMapping(value = "/pathtraver")
public class CookieValue_01 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("CookieValue_01");

    @ResponseBody
    @RequestMapping(value = "/BS00001", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        javax.servlet.http.Cookie[] theCookies = request.getCookies();
        String param = "noCookieValueSupplied";
        if (theCookies != null) {
            for (javax.servlet.http.Cookie theCookie : theCookies) {
                if ("BS00001".equals(theCookie.getName())) {
                    param = java.net.URLDecoder.decode(theCookie.getValue(), "UTF-8");
                    break;
                }
            }
        }
        String fileName = null;
        java.io.FileInputStream fis = null;
        try {
            fileName = testfileDir + param;
            fis = new java.io.FileInputStream(new java.io.File(fileName));
            byte[] b = new byte[1000];
            int size = fis.read(b);
            response.getWriter().println(
                    "The beginning of file: '" + ESAPI.encoder().encodeForHTML(fileName)
                            + "' is:\n\n" + ESAPI.encoder().encodeForHTML(new String(b, 0, size))
            );
        } catch (Exception e) {
            log.error("doPost . Couldn't open FileInputStream on file:{}", fileName);
            response.getWriter().println(
                    "Problem getting FileInputStream: "
                            + ESAPI.encoder().encodeForHTML(e.getMessage())
            );
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    log.error("doPost close . the exception:{} . ", e.getMessage());
                }
            }
        }
    }

}