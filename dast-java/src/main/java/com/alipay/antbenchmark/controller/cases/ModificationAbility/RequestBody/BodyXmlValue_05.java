package com.alipay.antbenchmark.controller.cases.ModificationAbility.RequestBody;

import com.alipay.antbenchmark.tools.Utils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
// compose = BodyXmlValue_05.java
// bind_url = /cmdi/BS00079
// assession information end


@Controller
@RequestMapping(value = "/cmdi")
public class BodyXmlValue_05 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BodyXmlValue_05");

    @ResponseBody
    @RequestMapping(value = "/BS00079", method = {RequestMethod.POST, RequestMethod.GET})
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
            if (!StringUtils.isEmpty(callbackMessage)) {
                Document doc = null;
                doc = DocumentHelper.parseText(callbackMessage);
                Element rootElt = doc.getRootElement();

//                String username = rootElt.element("username").getTextTrim();
//                String password = rootElt.element("password").getTextTrim();
                param = rootElt.element("BS00079").getTextTrim();
            }

            String a1 = "";
            String a2 = "";
            String osName = System.getProperty("os.name");
            if (osName.indexOf("Windows") != -1) {
                a1 = "cmd.exe";
                a2 = "/c";
            } else {
                a1 = "sh";
                a2 = "-c";
            }
            String[] args = {a1, a2, param};
            ProcessBuilder pb = new ProcessBuilder();
            pb.command(args);
            try {
                Process p = pb.start();
                Utils.printOSCommandResults(p, response);
            } catch (IOException e) {
                log.error("Problem executing cmdi - java.lang.ProcessBuilder(java.util.List) Test Case");
                throw new ServletException(e);
            }
        } catch (Exception e) {
            log.error("doPost error. Exception :{}", e.getMessage());
            response.getWriter().println(e.toString());
        }
    }
}
