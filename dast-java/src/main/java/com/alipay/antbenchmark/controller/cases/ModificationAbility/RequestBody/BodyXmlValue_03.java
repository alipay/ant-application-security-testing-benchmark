package com.alipay.antbenchmark.controller.cases.ModificationAbility.RequestBody;

import com.alipay.antbenchmark.tools.DatabaseHelper;
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
// compose = BodyXmlValue_03.java
// bind_url = /sqli/BS00068
// assession information end


@Controller
@RequestMapping(value = "/sqli")
public class BodyXmlValue_03 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BodyXmlValue_03");

    @ResponseBody
    @RequestMapping(value = "/BS00068", method = {RequestMethod.POST, RequestMethod.GET})
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
                param = rootElt.element("BS00068").getTextTrim();
            }
            if (param == null) {
                param = "";
            }
            String sql = "SELECT * from USERS where USERNAME='foo' and PASSWORD='" + param + "'";
            response.getWriter().println(param);
            try {
                org.springframework.jdbc.support.rowset.SqlRowSet results = DatabaseHelper.JDBCtemplate.queryForRowSet(sql);
                response.getWriter().println("Your results are: ");
                while (results.next()) {
                    response.getWriter().println(org.owasp.esapi.ESAPI.encoder().encodeForHTML(results.getString("USERNAME")) + " ");
                }
            } catch (org.springframework.dao.EmptyResultDataAccessException e) {
                response.getWriter().println("No results returned for query: " + org.owasp.esapi.ESAPI.encoder().encodeForHTML(sql));
            } catch (org.springframework.dao.DataAccessException e) {
                if (DatabaseHelper.hideSQLErrors) {
                    response.getWriter().println("Error processing request.");
                } else {
                    throw new ServletException(e);
                }
            }
        } catch (Exception e) {
            log.error("doPost error. Exception :{}", e.getMessage());
        }


    }

    public class RequestSimple {
        String username;
        String password;
        String BS00068;
    }
}
