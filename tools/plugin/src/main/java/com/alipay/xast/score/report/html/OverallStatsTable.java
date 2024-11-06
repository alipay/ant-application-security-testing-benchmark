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
package com.alipay.xast.score.report.html;

import static com.alipay.xast.score.report.Formats.fourDecimalPlacesNumber;
import static com.alipay.xast.score.report.Formats.twoDecimalPlacesPercentage;

import com.alipay.xast.score.Configuration;
import com.alipay.xast.score.Tool;
import com.alipay.xast.score.ToolResults;
import java.util.Set;

public class OverallStatsTable {

    private final Configuration config;
    private final String testSuite;

    public OverallStatsTable(Configuration config, String testSuite) {
        this.config = config;
        this.testSuite = testSuite;
    }

    /**
     * Generate the overall stats table across all the tools for the bottom of the home page.
     *
     * @param tools - The set of all tools being scored. Each Tool includes it's scored results.
     * @return The HTML of the overall stats table.
     */
    public String generateFor(Set<Tool> tools) {
        HtmlStringBuilder htmlBuilder = new HtmlStringBuilder();

        htmlBuilder.beginTable("table");

        addHeaderTo(htmlBuilder);

        tools.stream()
                .filter(tool -> !(config.showAveOnlyMode && tool.isCommercial()))
                .forEach(tool -> appendRowTo(htmlBuilder, tool));

        htmlBuilder.endTable();

        htmlBuilder.p(
                "*-Please refer to each tool's scorecard for the data used to calculate these values.");

        return htmlBuilder.toString();
    }

    private void addHeaderTo(HtmlStringBuilder htmlBuilder) {
        htmlBuilder.beginTr();
        htmlBuilder.th("Tool");

        if (config.mixedMode) {
            htmlBuilder.th(testSuite + " Version");
        }

        htmlBuilder.th("Type");

        if (config.includePrecision) {
            htmlBuilder.th("Precision*");
            htmlBuilder.th("F-score*");
        }

        htmlBuilder.th("${tprlabel}*");
        htmlBuilder.th("FPR*");
        htmlBuilder.th("Score*");

        htmlBuilder.endTr();
    }

    private void appendRowTo(HtmlStringBuilder htmlBuilder, Tool tool) {
        ToolResults results = tool.getOverallResults();

        htmlBuilder.beginTr(cssClassFor(results));
        htmlBuilder.td(tool.getToolNameAndVersion());

        if (config.mixedMode) {
            htmlBuilder.td(tool.getTestSuiteVersion());
        }

        htmlBuilder.td(tool.getToolType().name());

        if (config.includePrecision) {
            htmlBuilder
                    .td(twoDecimalPlacesPercentage.format(results.getPrecision()))
                    .td(fourDecimalPlacesNumber.format(results.getFScore()));
        }

        htmlBuilder
                .td(twoDecimalPlacesPercentage.format(results.getTruePositiveRate()))
                .td(twoDecimalPlacesPercentage.format(results.getFalsePositiveRate()))
                .td(twoDecimalPlacesPercentage.format(results.getOverallScore()))
                .endTr();
    }

    private String cssClassFor(ToolResults results) {
        String cssClass = null;

        if (isDanger(results)) {
            cssClass = "danger";
        } else if (isSuccess(results)) {
            cssClass = "success";
        }

        return cssClass;
    }

    private boolean isSuccess(ToolResults results) {
        return results.getTruePositiveRate() > .7 && results.getFalsePositiveRate() < .3;
    }

    private boolean isDanger(ToolResults results) {
        return Math.abs(results.getTruePositiveRate() - results.getFalsePositiveRate()) < .1;
    }
}
