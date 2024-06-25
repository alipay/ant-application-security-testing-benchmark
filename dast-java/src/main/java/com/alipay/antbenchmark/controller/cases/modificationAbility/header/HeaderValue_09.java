package com.alipay.antbenchmark.controller.cases.modificationAbility.header;

import com.alipay.antbenchmark.tools.Utils;
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
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


// assession information start
// real vulnerability = true
// assession project = 改包能力->header->value
// compose = HeaderValue_09.java
// bind_url = /cmdi/BS00027
// assession information end



@Controller
@RequestMapping(value = "/cmdi")
public class HeaderValue_09 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("HeaderValue_09");

    @ResponseBody
    @RequestMapping(value = "/BS00027", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String param = "";
        java.util.Enumeration<String> headers = request.getHeaders("BS00027");
        if (headers != null && headers.hasMoreElements()) {
            param = headers.nextElement();
        }
        param = URLDecoder.decode(param, "UTF-8");
        String bar = "";
        if (param != null) {
            bar = new String(Base64.decodeBase64(
                    Base64.encodeBase64(param.getBytes())));
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
        ProcessBuilder pb = new ProcessBuilder();
        pb.command(argList);
        try {
            Process p = pb.start();
            Utils.printOSCommandResults(p, response);
        } catch (IOException e) {
            log.error("Problem executing cmdi - java.lang.ProcessBuilder(java.util.List) Test Case");
            throw new ServletException(e);
        }
    }

}
