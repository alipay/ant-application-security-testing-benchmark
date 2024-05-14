package com.alipay.antbenchmark.controller.cases.ModificationAbility.RequestBody;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// assession information start
// real vulnerability = true
// assession project = 改包能力->requestBody->设置多个值
// compose = MultipleValues_02.java
// bind_url = /xpath/BS00139
// assession information end


@Controller
@RequestMapping(value = "/xpath")
public class MultipleValues_02 {

    private static final Logger log = LoggerFactory.getLogger("MultipleValues_02");


    @RequestMapping(value = "/BS00139", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fileName = "employees.xml";
            File d = new File(fileName);
            XPathFactory factory = XPathFactory.newInstance();
            XPath path = factory.newXPath();
            InputStream is = new FileInputStream(d);
            InputSource inputSource = new InputSource(is);
            String expression = "//users/user[username/text()='" + username + "' and password/text()='" + password + "']" + "/secret/text()";
            NodeList nodes = (NodeList) path.evaluate(expression, inputSource, XPathConstants.NODESET);
            StringBuffer responsestr = new StringBuffer();
            List json = new ArrayList();
            for (int i = 0; i < nodes.getLength(); i++) {
                java.util.Map<String, Object> employeeJson = new HashMap<>();
                json.add(employeeJson);
                Node node = nodes.item(i);
                employeeJson.put(((Node) node).getNodeName(), node.getTextContent());
            }
            responsestr.append(json);
            response.getWriter().println(responsestr.toString());
        } catch (Exception e) {
            response.getWriter().println(e.toString());
            log.error("doPost error. Exception :{}", e.getMessage());
        }
    }
}

