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
 * PURPOSE. See the GNU General Public License for more details
 *
 * @author Dave Wichers
 * @created 2015
 */
package com.alipay.xast.score.parsers;

import com.alipay.xast.score.ResultFile;
import com.alipay.xast.score.TestCaseResult;
import com.alipay.xast.score.TestSuiteResults;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class PMDReader extends Reader {

    @Override
    public boolean canRead(ResultFile resultFile) {
        return resultFile.filename().endsWith(".xml") && resultFile.xmlRootNodeName().equals("pmd");
    }

    @Override
    public TestSuiteResults parse(ResultFile resultFile) throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        // Prevent XXE
        docBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        InputSource is = new InputSource(new FileInputStream(resultFile.file()));
        Document doc = docBuilder.parse(is);

        TestSuiteResults tr = new TestSuiteResults("pmd", false, TestSuiteResults.ToolType.SAST);

        // If the filename includes an elapsed time in seconds (e.g., TOOLNAME-seconds.xml), set the
        // compute time on the scorecard.
        tr.setTime(resultFile.file());

        Node root = doc.getDocumentElement();
        String version = getAttributeValue("version", root);

        NodeList rootList = root.getChildNodes();
        tr.setToolVersion(version);

        List<Node> fileList = getNamedNodes("file", rootList);

        for (Node file : fileList) {
            List<TestCaseResult> tcrs = parsePMDItem(file);
            for (TestCaseResult tcr : tcrs) {
                tr.getTcrs().add(tcr);
            }
        }

        return tr;
    }

    private List<TestCaseResult> parsePMDItem(Node fileNode) {
        List<TestCaseResult> results = new ArrayList<TestCaseResult>();
        String filename = fileNode.getAttributes().getNamedItem("name").getNodeValue();

        List<Node> violationNodes = getNamedChildren("violation", fileNode);
        for (Node violationNode : violationNodes) {
            String violation = violationNode.getAttributes().getNamedItem("rule").getNodeValue();
            String testclass = filename.substring(filename.lastIndexOf("/") + 1);
            TestCaseResult tcr = new TestCaseResult();
            tcr.setTestCaseName(testclass);
            tcr.setCWE(figureCWE(violation));
            tcr.setCategory(violation);
            tcr.setEvidence(violation);
            results.add(tcr);
        }

        return results;
    }

    private int figureCWE(String rule) {
        switch (rule) {
            case "AvoidUsingOctalValues":
            case "CollapsibleIfStatements":
            case "EmptyCatchBlock":
            case "EmptyFinallyBlock":
            case "EmptyIfStmt":
            case "EmptyStatementNotInLoop":
            case "EmptySwitchStatements":
            case "UnnecessaryConversionTemporary":
            case "UnnecessaryFullyQualifiedName":
            case "UnnecessaryModifier":
            case "UnnecessaryReturn":
            case "UnusedFormalParameter":
            case "UnusedImports":
            case "UnusedLocalVariable":
            case "UnusedPrivateMethod":
            case "UselessParentheses":
                return 0000; // Don't care
                // Don't think PMD reports any of these:
            case "??1":
                return 614; // insecure cookie use
            case "??2":
                return 330; // weak random
            case "??3":
                return 90; // LDAP injection
            case "??4":
                return 22; // path traversal
            case "??5":
                return 22; // path traversal
            case "??6":
                return 327; // weak encryption
            case "??7":
                return 643; // xpath injection
            case "??8":
                return 328; // weak hash
            case "??9":
                return 78; // command injection
            case "??10":
                return 79; // XSS

                // FbInfer additional rules
            case "RESOURCE_LEAK":
            case "NULL_DEREFERENCE":
                return 0;

            default:
                System.out.println("Unknown category: " + rule);
        }

        return 0;
    }
}
