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

import com.alipay.xast.score.TP_FN_TN_FP_Counts;
import com.alipay.xast.score.TestSuiteResults;
import com.alipay.xast.score.Tool;
import com.alipay.xast.score.ToolResults;
import java.util.HashMap;
import java.util.Map;

public class ToolBuilder {

    private TestSuiteResults testSuiteResults = TestSuiteResultsBuilder.builder().build();
    private Map<String, TP_FN_TN_FP_Counts> scores = new HashMap<>();
    private ToolResults toolResults = new ToolResults();
    private String actualCsvResultFileName = "";
    private boolean isCommercial = false;

    private ToolBuilder() {}

    public static ToolBuilder builder() {
        return new ToolBuilder();
    }

    public ToolBuilder setTestSuiteResults(TestSuiteResults testSuiteResults) {
        this.testSuiteResults = testSuiteResults;

        return this;
    }

    public ToolBuilder setScores(Map<String, TP_FN_TN_FP_Counts> scores) {
        this.scores = scores;

        return this;
    }

    public ToolBuilder putScore(String key, TP_FN_TN_FP_Counts value) {
        this.scores.put(key, value);

        return this;
    }

    public ToolBuilder setToolResults(ToolResults toolResults) {
        this.toolResults = toolResults;

        return this;
    }

    public ToolBuilder setActualCsvResultFileName(String actualCsvResultFileName) {
        this.actualCsvResultFileName = actualCsvResultFileName;

        return this;
    }

    public ToolBuilder setIsCommercial(boolean isCommercial) {
        this.isCommercial = isCommercial;

        return this;
    }

    public Tool build() {
        return new Tool(
                testSuiteResults, scores, toolResults, actualCsvResultFileName, isCommercial);
    }
}
