package com.alipay.antbenchmark.controller.cases.ModificationAbility.RequestBody;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;

// assession information start
// real vulnerability = true
// assession project = 改包能力->requestBody->xml->value
// compose = BodyXmlValue_01.java
// bind_url = /xxe/BS00061
// assession information end

@Controller
@RequestMapping(value = "/xxe")
public class BodyXmlValue_01 extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @ResponseBody
    @RequestMapping(value = "/BS00061", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String param = request.getParameter("BS00061");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(param);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);

            // 遍历xml节点name和value
            StringBuffer buf = new StringBuffer();
            NodeList rootNodeList = document.getChildNodes();
            for (int i = 0; i < rootNodeList.getLength(); i++) {
                Node rootNode = rootNodeList.item(i);
                NodeList child = rootNode.getChildNodes();
                for (int j = 0; j < child.getLength(); j++) {
                    Node node = child.item(j);
                    buf.append(node.getNodeName() + ": " + node.getTextContent() + "\n");
                }
            }
            sr.close();
            response.getWriter().println(buf.toString());
        } catch (Exception e) {
            response.getWriter().println(e.toString());
        }

    }
}
