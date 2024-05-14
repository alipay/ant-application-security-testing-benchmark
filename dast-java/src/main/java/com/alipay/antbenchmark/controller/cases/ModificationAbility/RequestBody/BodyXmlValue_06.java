package com.alipay.antbenchmark.controller.cases.ModificationAbility.RequestBody;

import com.alipay.antbenchmark.tools.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

// assession information start
// real vulnerability = true
// assession project = 改包能力->requestBody->xml->标签值
// compose = BodyXmlValue_06.java
// bind_url = /pathtraver/BS00081
// assession information end


@Controller
@RequestMapping(value = "/pathtraver")
public class BodyXmlValue_06 extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger("BodyXmlValue_06");


    @ResponseBody
    @RequestMapping(value = "/BS00081", method = {RequestMethod.POST, RequestMethod.GET})
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String fileName = null;
        FileInputStream fis = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            BufferedReader br = request.getReader();
            String str = "";
            String listString = "";
            while ((str = br.readLine()) != null) {
                listString += str;
            }
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = null;
            try {
                db = dbf.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                log.error("doPost error. ParserConfigurationException :{}", e.getMessage());
            }
            StringReader sr = new StringReader(listString);
            InputSource is = new InputSource(sr);
            Document document = null;
            try {
                document = db.parse(is);
            } catch (SAXException e) {
                log.error("doPost error. SAXException :{}", e.getMessage());
            }
            String param = "";
            // 遍历xml节点name和value
            StringBuffer buf = new StringBuffer();
            NodeList rootNodeList = document.getChildNodes();
            for (int i = 0; i < rootNodeList.getLength(); i++) {
                Node rootNode = rootNodeList.item(i);
                NodeList child = rootNode.getChildNodes();
                for (int j = 0; j < child.getLength(); j++) {
                    Node node = child.item(j);
                    buf.append(node.getNodeName() + ": " + node.getTextContent() + "\n");
                    if ("BS00081".equals(node.getNodeName())) {
                        param = node.getTextContent();
                    }
                }
            }
            try {
                fileName = Utils.testfileDir + param;
                fis = new FileInputStream(new File(fileName));
                byte[] b = new byte[1000];
                int size = fis.read(b);
                response.getWriter().println(new String(b, 0, size));
            } catch (Exception e) {
                log.error("Couldn't open FileInputStream on file:{}", fileName);
                response.getWriter().println("Problem getting FileInputStream: " + org.owasp.esapi.ESAPI.encoder().encodeForHTML(e.getMessage()));
            }
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
