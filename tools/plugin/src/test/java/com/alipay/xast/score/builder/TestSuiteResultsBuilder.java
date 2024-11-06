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
 * @author Sascha Knoop
 * @created 2024
 */
package com.alipay.xast.score.builder;

import com.alipay.xast.score.TestSuiteResults;

public class TestSuiteResultsBuilder {

    private String toolname = "Super Tool";
    private boolean isCommercial = false;
    private TestSuiteResults.ToolType toolType = TestSuiteResults.ToolType.SAST;
    private String version = "47.11";
    private String testSuiteVersion = "1.2";

    private TestSuiteResultsBuilder() {}

    public static TestSuiteResultsBuilder builder() {
        return new TestSuiteResultsBuilder();
    }

    public TestSuiteResultsBuilder setToolname(String toolname) {
        this.toolname = toolname;

        return this;
    }

    public TestSuiteResultsBuilder setIsCommercial(boolean isCommercial) {
        this.isCommercial = isCommercial;

        return this;
    }

    public TestSuiteResultsBuilder setToolType(TestSuiteResults.ToolType toolType) {
        this.toolType = toolType;

        return this;
    }

    public TestSuiteResultsBuilder setToolVersion(String version) {
        this.version = version;

        return this;
    }

    public TestSuiteResults build() {
        TestSuiteResults testSuiteResults = new TestSuiteResults(toolname, isCommercial, toolType);

        testSuiteResults.setToolVersion(version);
        testSuiteResults.setTestSuiteVersion(testSuiteVersion);

        return testSuiteResults;
    }

    public TestSuiteResultsBuilder setTestSuiteVersion(String testSuiteVersion) {
        this.testSuiteVersion = testSuiteVersion;

        return this;
    }
}
