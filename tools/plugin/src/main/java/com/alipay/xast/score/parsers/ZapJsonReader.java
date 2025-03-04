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
 * <p>This reader reads JSON reports from ZAP open source tool at:
 * https://github.com/zaproxy/zaproxy
 *
 * @author Sascha Knoop
 * @created 2021
 */
package com.alipay.xast.score.parsers;

import com.alipay.xast.score.BenchmarkScore;
import com.alipay.xast.score.CweNumber;
import com.alipay.xast.score.ResultFile;
import com.alipay.xast.score.TestCaseResult;
import com.alipay.xast.score.TestSuiteResults;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ZapJsonReader extends Reader {

    // result keys for ZAP <= 2.10.x
    private static final String[] oldFormatKeys = {
        "sourceid", "other", "method", "evidence", "pluginId", "cweid", "confidence", "wascid"
    };

    // result keys for ZAP >= 2.11.0
    private static final String[] newFormatKeys = {"@version", "@generated", "site"};

    @Override
    public boolean canRead(ResultFile resultFile) {
        try {
            return isOldZapJson(resultFile) || isNewZapJson(resultFile);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isOldZapJson(ResultFile resultFile) {
        return resultFile.json().has("vulnerabilities")
                && hasExpectedKeys(
                        resultFile.json().getJSONArray("vulnerabilities").getJSONObject(0),
                        oldFormatKeys);
    }

    private static boolean hasExpectedKeys(JSONObject vulnerability, String[] expectedKeys) {
        for (String key : expectedKeys) {
            if (!vulnerability.has(key)) {
                return false;
            }
        }

        return true;
    }

    private boolean isNewZapJson(ResultFile resultFile) {
        return hasExpectedKeys(resultFile.json(), newFormatKeys);
    }

    @Override
    public TestSuiteResults parse(ResultFile resultFile) throws Exception {
        TestSuiteResults tr =
                new TestSuiteResults("OWASP ZAP", false, TestSuiteResults.ToolType.DAST);

        if (isOldZapJson(resultFile)) {
            handleOldReportFormat(resultFile, tr);
        } else {
            handleNewReportFormat(resultFile, tr);
        }

        return tr;
    }

    private void handleOldReportFormat(ResultFile resultFile, TestSuiteResults tr) {
        JSONArray arr = resultFile.json().getJSONArray("vulnerabilities");

        for (int i = 0; i < arr.length(); i++) {
            try {
                TestCaseResult tcr = new TestCaseResult();
                JSONObject finding = arr.getJSONObject(i);

                String testName = extractTestName(finding.getString("url"));

                if (testName.contains(BenchmarkScore.TESTCASENAME)) {
                    tcr.setNumber(testNumber(testName));
                    tcr.setCWE(figureCwe(finding));

                    tr.put(tcr);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleNewReportFormat(ResultFile resultFile, TestSuiteResults tr) {
        try {
            ReportV2_11 report = jsonMapper.readValue(resultFile.content(), ReportV2_11.class);

            tr.setToolVersion(report.version);

            report.sites.forEach(
                    site ->
                            site.alerts.forEach(
                                    alert -> {
                                        int cwe = mapCwe(alert.cwe);

                                        alert.instances.forEach(
                                                instance -> {
                                                    String testName = extractTestName(instance.uri);

                                                    if (testName.contains(
                                                            BenchmarkScore.TESTCASENAME)) {
                                                        TestCaseResult tcr = new TestCaseResult();

                                                        tcr.setNumber(testNumber(testName));
                                                        tcr.setCWE(cwe);

                                                        tr.put(tcr);
                                                    }
                                                });
                                    }));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private String extractTestName(String fullUrl) {
        try {
            // get rid of everything except the test name
            return new File(new URL(fullUrl).getPath()).getName().replace(".html", "");
        } catch (MalformedURLException e) {
            return "";
        }
    }

    private int figureCwe(JSONObject finding) {
        return mapCwe(finding.getString("cweid"));
    }

    static int mapCwe(String cwe) {
        switch (cwe) {
            case "22":
                return CweNumber.PATH_TRAVERSAL;
            case "78":
                return CweNumber.COMMAND_INJECTION;
            case "79":
                return CweNumber.XSS;
            case "89":
                return CweNumber.SQL_INJECTION;
            case "90":
                return CweNumber.LDAP_INJECTION;
            case "264":
            case "284":
                return CweNumber.IMPROPER_ACCESS_CONTROL;

            case "352":
                return CweNumber.CSRF;
            case "614":
                return CweNumber.INSECURE_COOKIE;

            case "1004":
                return CweNumber.COOKIE_WITHOUT_HTTPONLY;

                // Don't care about these:
            case "-1": // Informational Alert
            case "0": // Informational Alert: Check for differences in response based on fuzzed User
                // Agent
            case "16": // Configuration
            case "20": // Improper Input Validation
            case "91": // XML Injection (aka Blind XPath Injection)
            case "120": // Classic Buffer Overflow (Not possible in Java)
            case "134": // Use of Externally-Controlled Format String
            case "200": // Exposure of Sensitive Information to Unauthorized Actor - When 500 errors
                // are returned
            case "345": // Insufficient Verification of Data Authenticity
            case "359": // Exposure of Private Personal Information to an Unauthorized Actor
            case "436": // Interpretation Conflict
            case "525": // Browser caching sensitive data
            case "565": // Reliance on Cookies without Validation and Integrity Checking
            case "693": // Protection Mechanism Failure
            case "829": // Inclusion of Functionality from Untrusted Control Sphere (e.g., CDN)
            case "933": // Security Misconfiguration
            case "1021": // Improper Restriction of Rendered UI Layers or Frames
            case "1275": // Sensitive Cookie with Improper SameSite Attribute
                return Integer.parseInt(cwe); // Return the CWE anyway.

            default:
                System.out.println(
                        "WARNING: ZAP CWE not mapped to expected test suite CWE: " + cwe);
                return Integer.parseInt(cwe);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ReportV2_11 {

        @JsonProperty("@version")
        public String version;

        @JsonProperty("site")
        public List<Site> sites;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Site {

        public List<Alert> alerts;

        @JsonIgnoreProperties(ignoreUnknown = true)
        private static class Alert {

            @JsonProperty("cweid")
            public String cwe;

            public List<Instance> instances;

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Instance {

                public String uri;
            }
        }
    }
}
