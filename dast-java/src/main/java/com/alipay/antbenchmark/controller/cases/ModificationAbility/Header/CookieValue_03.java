package com.alipay.antbenchmark.controller.cases.ModificationAbility.Header;

import com.alipay.antbenchmark.tools.Utils;
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
import java.util.ArrayList;
import java.util.List;


// assession information start
// real vulnerability = true
// assession project = 改包能力->header->cookie中的value
// compose = CookieValue_03.java
// bind_url = /cmdi/BS00020
// assession information end


@Controller
@RequestMapping(value = "/cmdi")
public class CookieValue_03 extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger("CookieValue_03");

    @ResponseBody
    @RequestMapping(value = "/BS00020", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Cookie[] theCookies = request.getCookies();
        String param = "noCookieValueSupplied";
        if (theCookies != null) {
            for (Cookie theCookie : theCookies) {
                if ("BS00020".equals(theCookie.getName())) {
                    param = URLDecoder.decode(theCookie.getValue(), "UTF-8");
                    break;
                }
            }
        }
        String bar;
        String guess = "ABC";
        char switchTarget = guess.charAt(2);
        switch (switchTarget) {
            case 'A':
                bar = param;
                break;
            case 'B':
                bar = "bobs_your_uncle";
                break;
            case 'C':
            case 'D':
                bar = param;
                break;
            default:
                bar = "bobs_your_uncle";
                break;
        }
        List<String> argList = new ArrayList<String>();
        String osName = System.getProperty("os.name");
        if (osName.indexOf("Windows") != -1) {
            argList.add("cmd.exe");
            argList.add("/c");
        } else {
            argList.add("sh");
            argList.add("-c");
        }
        argList.add("echo " + bar);
        ProcessBuilder pb = new ProcessBuilder(argList);
        try {
            Process p = pb.start();
            Utils.printOSCommandResults(p, response);
        } catch (IOException e) {
            log.error("Problem executing cmdi - java.lang.ProcessBuilder(java.util.List) Test Case");
            throw new ServletException(e);
        }
    }

}
