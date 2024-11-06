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
 * @author Dave Wichers
 * @created 2015
 */
package com.alipay.xast.score;

import com.alipay.xast.tools.AbstractTestCaseRequest;

/* This class represents a single test case result. It documents the expected result (real),
 * and the actual result (result).
 */

public class TestCaseResult {
    private String testCaseName;
    private String url;
    private String assesionProject;
    private String realVul;
    private String compose;
    private String identifiedVul;
    private boolean matchResult = false;

    private int number = 0;
    private boolean truePositive = false; // Is this test case a true or false positive?
    private boolean result = false; // Did a tool properly detect this as a true or false positive?
    private int CWE = 0;
    private String category = null;
    private String evidence = null;
    private int confidence = 0;

    // optional attributes
    private String source = null;
    private String dataflow = null;
    private String sink = null;

    public TestCaseResult() {
        // By default, do nothing special.
    }

    /**
     * Convert what we know about a TestCase Request description back into a TestCaseResult
     * (expected or actual)
     *
     * @param request The request object used to access this test case.
     */
    public TestCaseResult(AbstractTestCaseRequest request) {
        this.testCaseName = request.getName();
        this.number = request.getNumber();
        this.truePositive = request.isVulnerability();
        this.CWE = request.getCategory().getCWE();
        this.category = request.getCategory().getName();

        // fill in optional attributes since we have this data available
        this.source = request.getSourceFile();
        this.dataflow = request.getDataflowFile();
        this.sink = request.getSinkFile();
    }

    public boolean isMatchResult() {
        return matchResult;
    }

    public void setMatchResult(boolean matchResult) {
        this.matchResult = matchResult;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAssesionProject() {
        return assesionProject;
    }

    public void setAssesionProject(String assesionProject) {
        this.assesionProject = assesionProject;
    }

    public String getCompose() {
        return compose;
    }

    public void setCompose(String compose) {
        this.compose = compose;
    }

    public String getRealVul() {
        return realVul;
    }

    public void setRealVul(String realVul) {
        this.realVul = realVul;
    }

    public String getIdentifiedVul() {
        return identifiedVul;
    }

    public void setIdentifiedVul(String identifiedVul) {
        this.identifiedVul = identifiedVul;
    }

    /*
     *  Set the name of the test case (E.g., BenchmarkTest00001). This is frequently only used for
     *  expected results, not actual results. Expected to actual can be correlated by the test number.
     */
    public void setTestCaseName(String name) {
        this.testCaseName = name;
    }

    /*
     * The name of the test case. E.g., BenchmarkTest00001
     */
    public String getName() {
        return testCaseName;
    }

    public int getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isTruePositive() {
        return truePositive;
    }

    public void setTruePositive(boolean truePositive) {
        this.truePositive = truePositive;
    }

    public boolean isPassed() {
        return result;
    }

    public void setPassed(boolean result) {
        this.result = result;
    }

    public int getCWE() {
        return CWE;
    }

    public void setCWE(int cwe) {
        this.CWE = cwe;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDataFlow() {
        return this.dataflow;
    }

    public void setDataFlow(String dataflow) {
        this.dataflow = dataflow;
    }

    public String getSink() {
        return this.sink;
    }

    public void setSink(String sink) {
        this.sink = sink;
    }

    @Override
    public String toString() {
        return "Testcase #: "
                + getNumber()
                + ", Category: "
                + getCategory()
                + ", isVulnerable: "
                + isTruePositive()
                + ", CWE: "
                + getCWE()
                + ", toolPassed: "
                + isPassed();
        /*                + ", evidence: "
                        + getEvidence()
                        + ", confidence: "
                        + getConfidence();
        */ }
}
