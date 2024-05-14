package com.alipay.antbenchmark.controller.cases.ModificationAbility.Header;

import com.alipay.antbenchmark.tools.Utils;
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
import java.net.URLDecoder;

// assession information start
// real vulnerability = false
// bind_url = /cmdi/BS00021
// assession information end

@Controller
@RequestMapping(value = "/cmdi")
public class CookieValue_04 extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger("CookieValue_04");

    @ResponseBody
    @RequestMapping(value = "/BS00021", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Cookie[] theCookies = request.getCookies();
        String param = "noCookieValueSupplied";
        if (theCookies != null) {
            for (Cookie theCookie : theCookies) {
                if ("BS00021".equals(theCookie.getName())) {
                    param = URLDecoder.decode(theCookie.getValue(), "UTF-8");
                    break;
                }
            }
        }
        String bar;
        int num = 86;
        if ((7 * 42) - num > 200) {
            bar = "This_should_always_happen";
        } else {
            bar = param;
        }
        String cmd = "";
        String osName = System.getProperty("os.name");
        if (osName.indexOf("Windows") != -1) {
            cmd = Utils.getOSCommandString("echo");
        }
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec(cmd + bar);
            Utils.printOSCommandResults(p, response);
        } catch (IOException e) {
            log.error("Problem executing cmdi - TestCase");
            response.getWriter().println(ESAPI.encoder().encodeForHTML(e.getMessage()));
            return;
        }
    }

}
