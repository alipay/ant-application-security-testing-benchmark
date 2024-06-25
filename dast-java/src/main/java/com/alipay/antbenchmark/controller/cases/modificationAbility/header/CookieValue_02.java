package com.alipay.antbenchmark.controller.cases.modificationAbility.header;

import com.alipay.antbenchmark.tools.Utils;
import org.apache.commons.codec.binary.Base64;
import org.owasp.esapi.ESAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


// assession information start
// real vulnerability = true
// assession project = 改包能力->header->cookie中的value
// compose = CookieValue_02.java
// bind_url = /pathtraver/BS00019
// assession information end

@Controller
@RequestMapping(value = "/pathtraver")
public class CookieValue_02 extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger("CookieValue_02");

    @ResponseBody
    @RequestMapping(value = "/BS00019", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Cookie[] theCookies = request.getCookies();
        String param = "noCookieValueSupplied";
        if (theCookies != null) {
            for (Cookie theCookie : theCookies) {
                if ("BS00019".equals(theCookie.getName())) {
                    param = URLDecoder.decode(theCookie.getValue(), "UTF-8");
                    break;
                }
            }
        }
        String bar = "";
        if (param != null) {
            bar = new String(Base64.decodeBase64(
                    Base64.encodeBase64(param.getBytes())));
        }
        String fileName = Utils.testfileDir + bar;
        InputStream is = null;

        try {
            Path path = Paths.get(fileName);
            is = Files.newInputStream(path, StandardOpenOption.READ);
            byte[] b = new byte[1000];
            int size = is.read(b);
            response.getWriter().println("The beginning of file: '" + ESAPI.encoder().encodeForHTML(fileName) + "' is:\n\n");
            response.getWriter().println(ESAPI.encoder().encodeForHTML(new String(b, 0, size)));
            is.close();
        } catch (Exception e) {
            log.error("Couldn't open InputStream on file: '" + fileName + "'");
            response.getWriter().println("Problem getting InputStream: " + ESAPI.encoder().encodeForHTML(e.getMessage()));
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    log.error("exception:{}", e.getMessage());
                }
            }
        }
    }

}
