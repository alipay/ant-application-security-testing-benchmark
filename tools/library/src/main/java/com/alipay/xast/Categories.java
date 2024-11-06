/**
 * OWASP Benchmark Project
 *
 * <p>This file is part of the Open Web Application Security Project (OWASP) Benchmark Project For
 * details, please see <a
 * href="https://owasp.org/www-project-benchmark/">https://owasp.org/www-project-benchmark/</a>.
 *
 * <p>The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation, version 2.
 *
 * <p>The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License for more details.
 *
 * @author David Anderson
 * @created 2021
 */
package com.alipay.xast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class contains all the vulnerability categories currently defined. It is implemented as a
 * Singleton.
 */
public class Categories {
    public static final String FILENAME = "categories.xml";

    private Map<String, Category> idToCategoryMap;
    private Map<String, Category> nameToCategoryMap;
    private List<Category> allCategories; // Alpha Sorted by shortname, longname, CWE?

    private static Categories _instance; // The Singleton instance of this class

    /**
     * Initialize all the categories from the InputStream connected to the target XML file.
     *
     * @param xmlFileStream - the InputStream from the categories.xml file.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public Categories(InputStream xmlFileStream)
            throws ParserConfigurationException, SAXException, IOException {
        if (_instance == null) {
            load(xmlFileStream);
            _instance = this;
        } else System.out.println("WARNING: Categories being initialized again by something.");
    }

    /**
     * Load the categories from the InputStream connected to the target XML file.
     *
     * @param xmlFileStream - the InputStream from the categories.xml file.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    private void load(InputStream xmlFileStream)
            throws ParserConfigurationException, SAXException, IOException {
        Map<String, Category> idToCategoryMap = new HashMap<String, Category>();
        Map<String, Category> nameToCategoryMap = new HashMap<String, Category>();
        List<Category> allCategories = new ArrayList<Category>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // avoid attacks like XML External Entities (XXE)
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

        DocumentBuilder db = dbf.newDocumentBuilder();

        Document document = db.parse(xmlFileStream);
        document.getDocumentElement().normalize();

        // Get all categories
        NodeList nList = document.getElementsByTagName("category");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // Print each ecategory's detail
                Element eElement = (Element) node;
                String id = eElement.getElementsByTagName("id").item(0).getTextContent();
                String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                NodeList cweNodeList = eElement.getElementsByTagName("cwe");
                // Default value -- CWEs included in expected results file. Might be used during
                // scoring.
                int cwe = 0;
                if (cweNodeList.getLength() > 0) {
                    cwe = Integer.parseInt(cweNodeList.item(0).getTextContent());
                }
                NodeList isInjectionNodeList = eElement.getElementsByTagName("isInjection");
                boolean isInjection = false; // Default value
                if (isInjectionNodeList.getLength() > 0) {
                    isInjection =
                            Boolean.parseBoolean(isInjectionNodeList.item(0).getTextContent());
                }
                String shortname =
                        eElement.getElementsByTagName("shortname").item(0).getTextContent();
                Category category = new Category(id, name, cwe, isInjection, shortname);
                // Lowercase both the ID and name, and getByID() and getByName() do the same to
                // facilitate matches
                idToCategoryMap.put(id.toLowerCase(), category);
                nameToCategoryMap.put(name.toLowerCase(), category);
                allCategories.add(category);
            }
        }

        this.idToCategoryMap = idToCategoryMap;
        this.nameToCategoryMap = nameToCategoryMap;
        Collections.sort(allCategories);
        this.allCategories = allCategories;
    }

    // NOTE: All these methods return the actual internal objects so COULD be modified by the caller
    // causing unexpected side affects.

    /** Get all the categories defined. They are returned in order by LONG name. */
    public static List<Category> getAllCategories() {
        if (_instance == null) {
            throw new NullPointerException("ERROR: Categories singleton not initialized");
        }
        return _instance.allCategories;
    }

    public static Category getById(String id) {
        if (_instance == null) {
            throw new NullPointerException("ERROR: Categories singleton not initialized");
        }
        return _instance.idToCategoryMap.get(id.toLowerCase());
    }

    public static Category getByName(String name) {
        if (_instance == null) {
            throw new NullPointerException("ERROR: Categories singleton not initialized");
        }
        return _instance.nameToCategoryMap.get(name.toLowerCase());
    }
}
