package com.alipay.antbenchmark.tools;


import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.security.SecureRandom;


public class XMLCrawler {
    private static final SecureRandom sr = new SecureRandom();

    public static String getAttributeValue(String name, Node node) {
        if (node == null) {
            return null;
        }
        NamedNodeMap nnm = node.getAttributes();
        if (nnm != null) {
            Node attrnode = nnm.getNamedItem(name);
            if (attrnode != null) {
                String value = attrnode.getNodeValue();
                if ("[random]".equals(value)) {
                    value = getToken();
                }
                return value;
            }
        }
        return null;
    }


    private static String getToken() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append((char) (sr.nextInt(26) + 'a'));
        }
        for (int i = 0; i < 3; i++) {
            sb.append((char) (sr.nextInt(10) + '0'));
        }
        return sb.toString();
    }
}

