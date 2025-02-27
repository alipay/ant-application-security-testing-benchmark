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
package com.alipay.xast.score.report;

import com.alipay.xast.Categories;
import com.alipay.xast.Category;
import com.alipay.xast.score.BenchmarkScore;
import com.alipay.xast.score.CategoryResults;
import com.alipay.xast.score.TP_FN_TN_FP_Counts;
import com.alipay.xast.score.Tool;
import com.alipay.xast.score.ToolResults;
import com.alipay.xast.score.report.ToolBarChart.BarChartType;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Map;

public class ToolReport {

    /**
     * Generate an HTML report for whatever tool's results are passed in.
     *
     * @param currentTool - the tool to generate a report for.
     * @param title - The title of the HTML report to generate.
     * @param scorecardImageFile - The File that contains the scorecard image for this tool's
     *     results.
     * @param actualCSVResultsFileName - The name of the actual results .csv file for this tool.
     * @return The generated HTML report for the supplied tool.
     * @throws IOException
     * @throws URISyntaxException
     */
    public static String generateHtml(
            Tool currentTool,
            String title,
            File scorecardImageFile,
            String actualCSVResultsFileName,
            Map<String, CategoryResults> overallAveToolResults)
            throws IOException, URISyntaxException {

        ToolResults overallToolResults = currentTool.getOverallResults();

        // Resources in a jar file have to be loaded as streams. Not directly as Files.
        InputStream templateFileStream =
                ToolReport.class
                        .getClassLoader()
                        .getResourceAsStream(BenchmarkScore.SCORECARDDIRNAME + "/template.html");
        String html = IOUtils.toString(templateFileStream, StandardCharsets.UTF_8);

        html =
                html.replace(
                        "${testsuite}", BenchmarkScore.fullTestSuiteName(BenchmarkScore.TESTSUITE));
        html = html.replace("${title}", title);
        html = html.replace("${tests}", Integer.toString(overallToolResults.getTotalTestCases()));
        html = html.replace("${time}", overallToolResults.getScanTime());
        html =
                html.replace(
                        "${score}",
                        ""
                                + new DecimalFormat("#0.00%")
                                        .format(overallToolResults.getOverallScore()));
        html = html.replace("${tool}", currentTool.getToolName());
        html = html.replace("${version}", currentTool.getTestSuiteVersion());
        html = html.replace("${projectlink}", BenchmarkScore.PROJECTLINKENTRY);
        html = html.replace("${cwecategoryname}", BenchmarkScore.config.cweCategoryName);
        html = html.replace("${actualResultsFile}", actualCSVResultsFileName);

        html = html.replace("${image}", scorecardImageFile.getName());

        String table = generateDetailedResultsTableForTool(currentTool, overallAveToolResults);
        html = html.replace("${table}", table);
        html = html.replace("${tprlabel}", BenchmarkScore.config.tprLabel);
        html =
                html.replace(
                        "${precisionkey}",
                        BenchmarkScore.PRECISIONKEYENTRY + BenchmarkScore.FSCOREKEYENTRY);

        // Calculate the image tags for the Precision/Recall tables, if includePrecision enabled
        String precisionTablesVal =
                (BenchmarkScore.config.includePrecision
                        ? precisionTablesVal =
                                "<img align=\"middle\" src=\""
                                        + ToolBarChart.generateBarChartFileName(
                                                currentTool, BarChartType.Precision)
                                        + "\" alt=\"\"/>\n<p />\n<p />\n"
                                        + "<img align=\"middle\" src=\""
                                        + ToolBarChart.generateBarChartFileName(
                                                currentTool, BarChartType.Recall)
                                        + "\" alt=\"\"/>\n<p />\n<p />\n"
                        : "");

        // Remove or replace the placeholder for the optional Precision and Recall tables.
        html = html.replace("${precisiontables}", precisionTablesVal);

        return html;
    }

    /** Generate a Detailed results table for whatever tool's results are passed in. */
    private static String generateDetailedResultsTableForTool(
            Tool tool, Map<String, CategoryResults> overallAveToolResults) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table class=\"table\">\n");
        sb.append("<tr>");
        sb.append("<th>Category</th>");
        if (BenchmarkScore.config.includePrecision) sb.append("<th>Abbr.</th>");
        sb.append("<th>CWE #</th>");
        sb.append("<th>TP</th>");
        sb.append("<th>FN</th>");
        sb.append("<th>TN</th>");
        sb.append("<th>FP</th>");
        sb.append("<th>Total</th>");
        if (BenchmarkScore.config.includePrecision) sb.append("<th>Precision</th><th>F-score</th>");
        sb.append("<th>${tprlabel}</th>");
        sb.append("<th>FPR</th>");
        sb.append("<th>Score</th>");
        sb.append("</tr>\n");
        TP_FN_TN_FP_Counts totals = new TP_FN_TN_FP_Counts();
        double totalPrecision = 0;
        double totalFScore = 0;
        double totalTPR = 0;
        double totalFPR = 0;
        double totalScore = 0;

        Map<String, TP_FN_TN_FP_Counts> scoresPerCategory = tool.getScores();
        ToolResults overallToolResults = tool.getOverallResults();
        for (String categoryName : scoresPerCategory.keySet()) {

            Category category = Categories.getByName(categoryName);

            TP_FN_TN_FP_Counts c = scoresPerCategory.get(categoryName);
            CategoryResults r = overallToolResults.getCategoryResults(categoryName);
            String style = "";

            if (Math.abs(r.truePositiveRate - r.falsePositiveRate) < .1) style = "class=\"danger\"";
            else if (r.truePositiveRate > .7 && r.falsePositiveRate < .3)
                style = "class=\"success\"";
            sb.append("<tr " + style + ">");
            sb.append("<td>" + categoryName + "</td>");
            if (BenchmarkScore.config.includePrecision) { // Abbr. column
                sb.append("<td>" + category.getShortName() + "</td>");
            }
            sb.append("<td>" + category.getCWE() + "</td>");
            sb.append("<td>" + c.tp + "</td>");
            sb.append("<td>" + c.fn + "</td>");
            sb.append("<td>" + c.tn + "</td>");
            sb.append("<td>" + c.fp + "</td>");
            sb.append("<td>" + r.totalTestCases + "</td>");
            String recallBonus = "";
            if (BenchmarkScore.config.includePrecision) {
                CategoryResults currentCategoryResults = overallAveToolResults.get(categoryName);
                // default value hard spaces equal to triangle width
                String precisionBonus = "&nbsp;&nbsp;&nbsp;&nbsp;";
                // r.precision has range 0-1, but currentCategoryResults.precision is 1 to 100.
                // FIXME: Fix precision calculations so they are the same units
                double precisionDiff = 100 * r.precision - currentCategoryResults.precision;
                if (precisionDiff >= 5)
                    precisionBonus =
                            "<span style=\"color: green\">&#9650;</span>"; // Green up triangle
                else if (precisionDiff <= -5) {
                    precisionBonus =
                            "<span style=\"color: red\">&#9660;</span>"; // Red down triangle
                }
                sb.append(
                        "<td>"
                                + precisionBonus
                                + new DecimalFormat("#0.00%").format(r.precision)
                                + "</td>");

                // default value hard spaces equal to triangle width
                String fscoreBonus = "&nbsp;&nbsp;&nbsp;&nbsp;";
                // FIXME: Fix fscore calculations so they are the same units
                double fscoreDiff = 100 * r.fscore - currentCategoryResults.fscore;
                if (fscoreDiff >= 5) fscoreBonus = "<span style=\"color: green\">&#9650;</span>";
                else if (fscoreDiff <= -5) {
                    fscoreBonus = "<span style=\"color: red\">&#9660;</span>";
                }
                sb.append(
                        "<td>"
                                + fscoreBonus
                                + new DecimalFormat("#0.0000").format(r.fscore)
                                + "</td>");

                // default value hard spaces equal to triangle width
                recallBonus = "&nbsp;&nbsp;&nbsp;&nbsp;";
                // FIXME: Fix truePositiveRate calculations so they are the same units
                double recallDiff =
                        100 * r.truePositiveRate - currentCategoryResults.truePositiveRate;
                if (recallDiff >= 5) recallBonus = "<span style=\"color: green\">&#9650;</span>";
                else if (recallDiff <= -5) {
                    recallBonus = "<span style=\"color: red\">&#9660;</span>";
                }
            }
            sb.append(
                    "<td>"
                            + recallBonus
                            + new DecimalFormat("#0.00%").format(r.truePositiveRate)
                            + "</td>");
            sb.append("<td>" + new DecimalFormat("#0.00%").format(r.falsePositiveRate) + "</td>");
            sb.append("<td>" + new DecimalFormat("#0.00%").format(r.score) + "</td>");
            sb.append("</tr>\n");
            totals.tp += c.tp;
            totals.fn += c.fn;
            totals.tn += c.tn;
            totals.fp += c.fp;
            totalPrecision += r.precision;
            totalFScore += r.fscore;
            totalTPR += r.truePositiveRate;
            totalFPR += r.falsePositiveRate;
            totalScore += r.score;
        }
        sb.append("<tr><th>Totals</th><th/>");
        if (BenchmarkScore.config.includePrecision) sb.append("<th/>"); // Abbr column added
        sb.append("<th>" + totals.tp + "</th>");
        sb.append("<th>" + totals.fn + "</th>");
        sb.append("<th>" + totals.tn + "</th>");
        sb.append("<th>" + totals.fp + "</th>");
        int total = totals.tp + totals.fn + totals.tn + totals.fp;
        sb.append("<th>" + total + "</th>");
        if (BenchmarkScore.config.includePrecision)
            sb.append("<th/><th/><th/><th/>"); // 4 columns added with this flag
        sb.append("<th/><th/><th/></tr>\n");

        sb.append("<tr><th>Overall Results*</th><th/><th/><th/><th/><th/><th/>");
        if (BenchmarkScore.config.includePrecision) {
            double precision = (totalPrecision / scoresPerCategory.size());
            sb.append("<th/><th>" + new DecimalFormat("#0.00%").format(precision) + "</th>");
            double fscore = (totalFScore / scoresPerCategory.size());
            sb.append("<th>" + new DecimalFormat("#0.0000").format(fscore) + "</th>");
        }
        double tpr = (totalTPR / scoresPerCategory.size());
        sb.append("<th>" + new DecimalFormat("#0.00%").format(tpr) + "</th>");
        double fpr = (totalFPR / scoresPerCategory.size());
        sb.append("<th>" + new DecimalFormat("#0.00%").format(fpr) + "</th>");
        double score = totalScore / scoresPerCategory.size();
        sb.append("<th>" + new DecimalFormat("#0.00%").format(score) + "</th>");
        sb.append("</tr>\n");
        if (BenchmarkScore.config.includePrecision) {
            sb.append(
                    "<tr><th colspan=\"3\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Legend:"
                            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "<span style=\"color: green\">&#9650;</span> = 5% or more above average</th>"
                            + "<th colspan=\"6\"><span style=\"color: red\">&#9660;</span> = 5% or more below average</th></tr>\n");
        }
        sb.append("</table>");
        sb.append(
                "<p>*-The Overall Results are averages across all the vulnerability categories. "
                        + " You can't compute these averages by simply calculating the"
                        + (BenchmarkScore.config.includePrecision
                                ? " Precision, F-score, Recall (TPR),"
                                : " TPR")
                        + " and FPR rates using"
                        + " the values in the Totals row. If you did that, categories with larger number of tests would carry "
                        + " more weight than categories with less tests. The proper calculation of the Overall Results is to"
                        + " add up the values of each of these per row, "
                        + " then divide by the number of rows, which is how they are calculated.<p/>");

        return sb.toString();
    }
}
