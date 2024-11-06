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
 * @author Nick Sanidas
 * @created 2015
 */
package com.alipay.xast;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CodeblockUtils {

    /**
     * This method opens the specified source XML file and returns the 1st element in it. This
     * method expects there to be only one element in the file and the element is named
     * <source-metadata>.
     *
     * @param xmlFile - The source XML file of interest.
     * @return The one XML Element in the file.
     */
    public static Element getSourceElementFromXMLFile(File xmlFile) {
        return getElementFromXMLFile(xmlFile, "source-metadata");
    }

    /**
     * This method opens the specified dataflow XML file and returns the 1st element in it. This
     * method expects there to be only one element in the file and the element is named
     * <dataflow-metadata>.
     *
     * @param xmlFile - The dataflow XML file of interest.
     * @return The one XML Element in the file.
     */
    public static Element getDataflowElementFromXMLFile(File xmlFile) {
        return getElementFromXMLFile(xmlFile, "dataflow-metadata");
    }

    /**
     * This method opens the specified sink XML file and returns the 1st element in it. This method
     * expects there to be only one element in the file and the element is named <sink-metadata>.
     *
     * @param xmlFile - The sink XML file of interest.
     * @return The one XML Element in the file.
     */
    public static Element getSinkElementFromXMLFile(File xmlFile) {
        return getElementFromXMLFile(xmlFile, "sink-metadata");
    }

    /**
     * This method opens the specified XML file and returns the 1st element in it specified by the
     * elementName parameter. This method expects there to be only one element in the file.
     *
     * @param xmlFile - The XML file of interest.
     * @param elementName - The name of the one element in the file.
     * @return The one XML Element in the file.
     */
    private static Element getElementFromXMLFile(File xmlFile, String elementName) {

        Element emetadata = null;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            // Prevent XXE = Note, disabling this entirely breaks Permutation of
            // Web Services, so have to use the alternate defense.
            // dbFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl",
            // true);
            dbFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            dbFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);

            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            NodeList nList = doc.getElementsByTagName(elementName);
            Node metadata = nList.item(0);

            if (metadata.getNodeType() != Node.ELEMENT_NODE) {
                throw new IOException(
                        "ERROR: WAS EXPECTING AN ELEMENT NODE of type: "
                                + elementName
                                + " in metadata XML file: "
                                + xmlFile);
            }

            emetadata = (Element) metadata;

        } catch (ParserConfigurationException | SAXException e1) {
            System.out.println("WARNING: Couldn't parse source metadata file.");
            e1.printStackTrace();
        } catch (IOException e) {
            System.out.println("WARNING: Couldn't find source metadata file.");
            e.printStackTrace();
        }

        return emetadata;
    }
}
