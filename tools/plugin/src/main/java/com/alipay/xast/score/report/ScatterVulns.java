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

import com.alipay.xast.score.BenchmarkScore;
import com.alipay.xast.score.CategoryResults;
import com.alipay.xast.score.TestSuiteResults;
import com.alipay.xast.score.Tool;
import com.alipay.xast.score.ToolResults;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ScatterVulns extends ScatterPlot {

    private double aveFalsePosRates = 0;
    private double aveTruePosRates = 0;
    private final String focus; // Tool to focus on, if any.

    // Most of these Non-Commercial, Commercial, and Overall values are accessible via getters.

    // Non-Commercial Scores
    private CategoryResults noncommercialCategResults;
    private int noncommercialToolCount = 0;
    private double noncommercialLow = 100;
    private TestSuiteResults.ToolType noncommercialLowToolType = null;
    private double noncommercialHigh = 0;
    private TestSuiteResults.ToolType noncommercialHighToolType = null;
    private double noncommercialAveScore = 0;
    private double noncommercialAvePrecision = 0;
    private double noncommercialAveTPR = 0;
    private double noncommercialAveFPR = 0;

    // Commercial Scores
    private CategoryResults commercialCategResults;
    private int commercialToolCount = 0;
    private double commercialLow = 100;
    private TestSuiteResults.ToolType commercialLowToolType = null;
    private double commercialHigh = 0;
    private TestSuiteResults.ToolType commercialHighToolType = null;
    private double commercialAveScore = 0;
    private double commercialAvePrecision = 0;
    private double commercialAveTPR = 0;
    private double commercialAveFPR = 0;

    // Overall Scores
    private CategoryResults overallCategResults;
    private int overallToolCount = 0;
    private double overallLow = 100;
    private TestSuiteResults.ToolType overallLowToolType = null;
    private double overallHigh = 0;
    private TestSuiteResults.ToolType overallHighToolType = null;
    private double overallAveScore = 0;
    private double overallAvePrecision = 0;
    private double overallAveTPR = 0;
    private double overallAveFPR = 0;

    // The category the above statistics across all the tools has been calculated for.
    public final String CATEGORY;

    /**
     * This calculates how all the tools did against the Benchmark in this vulnerability category
     *
     * @param title - The title of the chart to be produced.
     * @param height - Height of the chart (typically 800)
     * @param category - The vulnerability category this chart is being generated for.
     * @param toolResults - A list of each individual tool's results.
     * @param focus - A tool to emphasize in the chart, if any.
     */
    public ScatterVulns(
            String title, int height, String category, Set<Tool> toolResults, String focus) {
        this.focus = focus;
        this.CATEGORY = category;
        display("          " + title, height, category, toolResults);
    }

    private JFreeChart display(String title, int height, String category, Set<Tool> toolResults) {

        // averages
        ArrayList<Double> averageFalseRates = new ArrayList<Double>();
        ArrayList<Double> averageTrueRates = new ArrayList<Double>();

        int commercialToolQuantity = 0;
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Scores");

        for (Tool tool : toolResults) {
            if (!tool.isCommercial()) {
                CategoryResults overallResult =
                        tool.getOverallResults().getCategoryResults(category);
                if (Double.isNaN(overallResult.falsePositiveRate)) {
                    System.out.println(
                            "ERROR: false positive rate for category: " + category + " is NaN");
                }
                if (Double.isNaN(overallResult.truePositiveRate)) {
                    System.out.println(
                            "ERROR: true positive rate for category: " + category + " is NaN");
                }
                series.add(
                        overallResult.falsePositiveRate * 100,
                        overallResult.truePositiveRate * 100);
            }
        }

        for (Tool tool : toolResults) {
            if (tool.isCommercial()) {
                CategoryResults overallResult =
                        tool.getOverallResults().getCategoryResults(category);
                if (!BenchmarkScore.config.showAveOnlyMode) {
                    if (Double.isNaN(overallResult.falsePositiveRate)) {
                        System.out.println(
                                "ERROR: false positive rate for category: " + category + " is NaN");
                    }
                    if (Double.isNaN(overallResult.truePositiveRate)) {
                        System.out.println(
                                "ERROR: true positive rate for category: " + category + " is NaN");
                    }
                    series.add(
                            overallResult.falsePositiveRate * 100,
                            overallResult.truePositiveRate * 100);
                }
                commercialToolQuantity++;
                averageFalseRates.add(overallResult.falsePositiveRate);
                averageTrueRates.add(overallResult.truePositiveRate);
            }
        }

        for (double d : averageFalseRates) {
            this.aveFalsePosRates += d;
        }
        this.aveFalsePosRates = this.aveFalsePosRates / averageFalseRates.size();

        for (double d : averageTrueRates) {
            this.aveTruePosRates += d;
        }
        this.aveTruePosRates = this.aveTruePosRates / averageTrueRates.size();

        if (commercialToolQuantity > 1
                || (BenchmarkScore.config.showAveOnlyMode && commercialToolQuantity == 1)) {
            if (Double.isNaN(this.aveFalsePosRates)) {
                System.out.println(
                        "ERROR: average false positive rate for category: " + category + " is NaN");
            }
            if (Double.isNaN(this.aveTruePosRates)) {
                System.out.println(
                        "ERROR: average true positive rate for category: " + category + " is NaN");
            }
            series.add(this.aveFalsePosRates * 100, this.aveTruePosRates * 100);
        }

        dataset.addSeries(series);
        this.chart =
                ChartFactory.createScatterPlot(
                        title,
                        "False Positive Rate",
                        "True Positive Rate",
                        dataset,
                        PlotOrientation.VERTICAL,
                        true,
                        true,
                        false);
        ScatterVulns.theme.apply(this.chart);
        initializePlot(this.chart);

        XYPlot xyplot = this.chart.getXYPlot();

        makeDataLabels(category, toolResults, xyplot);
        makeLegend(category, toolResults, 103, 100.5, dataset, xyplot);

        for (XYDataItem item : (List<XYDataItem>) series.getItems()) {
            double x = item.getX().doubleValue();
            double y = item.getY().doubleValue();
            double z = (x + y) / 2;
            XYLineAnnotation score = new XYLineAnnotation(x, y, z, z, dashed, Color.blue);
            xyplot.addAnnotation(score);
        }

        return this.chart;
    }

    /**
     * Add the letter, from the key on the right, next to the plot point on the chart for for each
     * tool to the supplied xyplot.
     *
     * @param tools - THe set of tool results.
     * @param xyplot - The chart to make the Data labels on.
     */
    private void makeDataLabels(String category, Set<Tool> toolResults, XYPlot xyplot) {
        HashMap<Point2D, String> map = makePointList(category, toolResults);
        addLabelsToPlotPoints(map, xyplot);
    }

    private SecureRandom sr = new SecureRandom();

    private HashMap<Point2D, String> makePointList(String category, Set<Tool> toolResults) {
        HashMap<Point2D, String> map = new HashMap<Point2D, String>();
        char ch = ScatterHome.INITIAL_LABEL;

        // make a list of all points. Add in a tiny random to prevent exact
        // duplicate coordinates in map
        int commercialToolQuantity = 0;

        for (Tool tool : toolResults) {
            if (!tool.isCommercial()) {
                CategoryResults or = tool.getOverallResults().getCategoryResults(category);
                // this puts the label just below the point
                double x = or.falsePositiveRate * 100 + sr.nextDouble() * .000001;
                double y = or.truePositiveRate * 100 + sr.nextDouble() * .000001 - 1;
                Point2D p = new Point2D.Double(x, y);
                String label = "" + ch;
                map.put(p, label);
                // Weak hack if there are more than 26 tools scored. This will only get us to 52.
                if (ch == 'Z') ch = 'a';
                else ch++;
            }
        }

        for (Tool tool : toolResults) {
            if (tool.isCommercial()) {
                commercialToolQuantity++;
                if (!BenchmarkScore.config.showAveOnlyMode) {
                    CategoryResults or = tool.getOverallResults().getCategoryResults(category);
                    // this puts the label just below the point
                    double x = or.falsePositiveRate * 100 + sr.nextDouble() * .000001;
                    double y = or.truePositiveRate * 100 + sr.nextDouble() * .000001 - 1;
                    Point2D p = new Point2D.Double(x, y);
                    String label = "" + ch;
                    map.put(p, label);
                    // Weak hack if more than 26 tools scored. This will only get us to 52
                    if (ch == 'Z') ch = 'a';
                    else ch++;
                }
            }
        }

        if (commercialToolQuantity > 1
                || (BenchmarkScore.config.showAveOnlyMode && commercialToolQuantity == 1)) {
            Point2D ap =
                    new Point2D.Double(
                            aveFalsePosRates * 100 + sr.nextDouble() * .000001,
                            aveTruePosRates * 100 + sr.nextDouble() * .000001 - 1);
            this.averageLabel = ch;
            map.put(ap, "" + ch);
        }
        dedupifyPlotPoints(map);
        return map;
    }

    /**
     * Create the Legend for this chart. And as a side effect, calculate the overall commercial
     * results for this vuln category.
     *
     * @param category
     * @param toolResults
     * @param x
     * @param y
     * @param dataset
     * @param xyplot
     */
    private void makeLegend(
            String category,
            Set<Tool> toolResults,
            double x,
            double y,
            XYSeriesCollection dataset,
            XYPlot xyplot) {

        char ch = ScatterHome.INITIAL_LABEL;
        int i = -2;

        // non-commercial results
        boolean printedNonCommercialLabel = false;
        double noncommercialTotalScore = 0;
        double noncommercialTotalPrecision = 0;
        double noncommercialTotalTPR = 0;
        double noncommercialTotalFPR = 0;

        for (Tool tool : toolResults) {
            if (!tool.isCommercial()) {
                // print non commercial label if there is at least one
                // non-commercial tool
                if (!printedNonCommercialLabel) {
                    addLabelToKey(xyplot, x, y, i, "Non-Commercial");
                    i++;
                    printedNonCommercialLabel = true;
                }

                ToolResults or = tool.getOverallResults();
                // Special hack to make it line up better if the letter is an 'I' or 'i'
                String label = (ch == 'I' || ch == 'i' ? ch + ":   " : ch + ": ");
                // Another hack to make it line up better if the letter is a 'J' or 'j'
                label = (ch == 'J' || ch == 'j' ? ch + ":  " : label);

                this.noncommercialToolCount++;
                this.overallToolCount++;

                addEntryToKey(
                        xyplot,
                        Color.blue,
                        x,
                        y,
                        i,
                        label,
                        tool.getToolNameAndVersion(),
                        // For ScatterVulns, these need to be the rates for this vuln cat
                        or.getCategoryResults(category).truePositiveRate,
                        or.getCategoryResults(category).falsePositiveRate);

                i++;
                // Weak hack if there are more than 26 tools scored. This will only get us to 52.
                if (ch == 'Z') ch = 'a';
                else ch++;

                noncommercialTotalScore += or.getCategoryResults(category).score;
                noncommercialTotalPrecision += or.getCategoryResults(category).precision;
                noncommercialTotalTPR += or.getCategoryResults(category).truePositiveRate;
                noncommercialTotalFPR += or.getCategoryResults(category).falsePositiveRate;

                double score = or.getCategoryResults(category).score * 100;
                if (score < noncommercialLow) {
                    this.noncommercialLow = score;
                    this.noncommercialLowToolType = tool.getToolType();
                }
                if (score > noncommercialHigh) {
                    this.noncommercialHigh = score;
                    this.noncommercialHighToolType = tool.getToolType();
                }

                if (score < overallLow) {
                    this.overallLow = score;
                    this.overallLowToolType = tool.getToolType();
                }
                if (score > overallHigh) {
                    this.overallHigh = score;
                    this.overallHighToolType = tool.getToolType();
                }
            }

            // non-commercial stats
            if (this.noncommercialToolCount > 0) {
                this.noncommercialAveScore = noncommercialTotalScore / this.noncommercialToolCount;
                this.noncommercialAvePrecision =
                        noncommercialTotalPrecision / this.noncommercialToolCount;
                this.noncommercialAveTPR = noncommercialTotalTPR / this.noncommercialToolCount;
                this.noncommercialAveFPR = noncommercialTotalFPR / this.noncommercialToolCount;
            }

            // We don't track the number of test cases across all these results, only the # of
            // tools. So we set test case count to -1
            this.noncommercialCategResults =
                    new CategoryResults(
                            this.CATEGORY,
                            this.noncommercialAvePrecision,
                            this.noncommercialAveTPR,
                            this.noncommercialAveFPR,
                            -1);
        }

        // commercial tools
        boolean printedCommercialLabel = false;
        double commercialTotalScore = 0;
        double commercialTotalPrecision = 0;
        double commercialTotalTPR = 0;
        double commercialTotalFPR = 0;
        final DecimalFormat DF = new DecimalFormat("#0.0");

        for (Tool tool : toolResults) {
            ToolResults or = tool.getOverallResults();
            if (tool.isCommercial()) {

                // print commercial label if there is at least one commercial tool
                if (!printedCommercialLabel) {
                    addLabelToKey(xyplot, x, y, i, "Commercial");
                    i++;
                    printedCommercialLabel = true;
                }

                this.commercialToolCount++;
                this.overallToolCount++;
                double score = or.getCategoryResults(category).score * 100;
                double tpr = or.getCategoryResults(category).truePositiveRate * 100;
                double fpr = or.getCategoryResults(category).falsePositiveRate * 100;
                // don't show the commercial tool results if in 'show ave only mode'
                if (!BenchmarkScore.config.showAveOnlyMode) {
                    // Special hack to make it line up better if the letter is an 'I' or 'i'
                    String label = (ch == 'I' || ch == 'i' ? ch + ":   " : ch + ": ");
                    // Another hack to make it line up better if the letter is a 'J' or 'j'
                    label = (ch == 'J' || ch == 'j' ? ch + ":  " : label);

                    addEntryToKey(
                            xyplot,
                            Color.blue,
                            x,
                            y,
                            i,
                            label,
                            tool.getToolNameAndVersion(),
                            or.getTruePositiveRate(),
                            or.getFalsePositiveRate());

                    i++; // increment the location of the label
                    // Weak hack if more than 26 tools scored. This will only get us to 52
                    if (ch == 'Z') ch = 'a';
                    else ch++;
                }
                commercialTotalScore += score;
                commercialTotalPrecision += or.getCategoryResults(category).precision * 100;
                commercialTotalTPR += tpr;
                commercialTotalFPR += fpr;

                if (score <= commercialLow) {
                    this.commercialLow = score;
                    this.commercialLowToolType = tool.getToolType();
                }
                if (score > commercialHigh) {
                    this.commercialHigh = score;
                    this.commercialHighToolType = tool.getToolType();
                }

                if (score < overallLow) {
                    this.overallLow = score;
                    this.overallLowToolType = tool.getToolType();
                }
                if (score > overallHigh) {
                    this.overallHigh = score;
                    this.overallHighToolType = tool.getToolType();
                }
            }

            // Add color emphasis to the tool of focus
            if (tool.getToolName().replace(' ', '_').equalsIgnoreCase(this.focus)) {
                CategoryResults orc = tool.getOverallResults().getCategoryResults(category);
                Point2D focusPoint =
                        new Point2D.Double(orc.falsePositiveRate * 100, orc.truePositiveRate * 100);
                Color green = new Color(0, 1, 0, 0.5f);
                makePoint(xyplot, focusPoint, 3, green);
            }
        }

        // commercial average stats
        if (this.commercialToolCount > 0) {
            this.commercialAveScore = commercialTotalScore / this.commercialToolCount;
            this.commercialAvePrecision = commercialTotalPrecision / this.commercialToolCount;
            this.commercialAveTPR = commercialTotalTPR / this.commercialToolCount;
            this.commercialAveFPR = commercialTotalFPR / this.commercialToolCount;
        }

        // We don't track the number of test cases across all these results, only the # of
        // tools. So we set test case count to -1
        this.commercialCategResults =
                new CategoryResults(
                        this.CATEGORY,
                        this.commercialAvePrecision,
                        this.commercialAveTPR,
                        this.commercialAveFPR,
                        -1);

        if (this.commercialToolCount > 1
                || (BenchmarkScore.config.showAveOnlyMode && this.commercialToolCount == 1)) {

            // The last two params need to be from 0 - 1, not 0 - 100, so we divide each by 100
            addEntryToKey(
                    xyplot,
                    Color.magenta,
                    x,
                    y,
                    i,
                    ch + ": ",
                    "Commercial Average",
                    commercialAveTPR / 100,
                    commercialAveFPR / 100);

            Point2D averagePoint =
                    new Point2D.Double(aveFalsePosRates * 100, aveTruePosRates * 100);
            makePoint(xyplot, averagePoint, 3, Color.magenta);
        }

        // overall stats
        this.overallAveScore =
                (commercialTotalScore + (this.noncommercialAveScore * this.noncommercialToolCount))
                        / this.overallToolCount;
        this.overallAvePrecision =
                (commercialTotalPrecision
                                + (this.noncommercialAvePrecision * this.noncommercialToolCount))
                        / this.overallToolCount;
        this.overallAveTPR =
                (commercialTotalTPR + (this.noncommercialAveTPR * this.noncommercialToolCount))
                        / this.overallToolCount;
        this.overallAveFPR =
                (commercialTotalFPR + (this.noncommercialAveFPR * this.noncommercialToolCount))
                        / this.overallToolCount;

        // We don't track the number of test cases across all these results, only the # of
        // tools. So we set test case count to -1
        this.overallCategResults =
                new CategoryResults(
                        this.CATEGORY,
                        this.overallAvePrecision,
                        this.overallAveTPR,
                        this.overallAveFPR,
                        -1);
    }

    public static ScatterVulns generateComparisonChart(
            String category, Set<Tool> toolResults, String focus, File scoreCardDir) {
        try {
            String scatterTitle =
                    BenchmarkScore.fullTestSuiteName(BenchmarkScore.TESTSUITE)
                            + (BenchmarkScore.config.mixedMode
                                    ? " -"
                                    : " v" + BenchmarkScore.TESTSUITEVERSION)
                            + " "
                            + category
                            + " Comparison";
            ScatterVulns scatter =
                    new ScatterVulns(scatterTitle, 800, category, toolResults, focus);
            scatter.writeChartToFile(
                    new File(
                            scoreCardDir,
                            BenchmarkScore.TESTSUITE
                                    + "_v"
                                    + BenchmarkScore.TESTSUITEVERSION
                                    + "_Scorecard_for_"
                                    + category.replace(' ', '_')
                                    + ".png"),
                    800);
            return scatter;
        } catch (IOException e) {
            System.out.println("Couldn't generate test suite vulnerability chart for some reason.");
            e.printStackTrace();
            return null;
        }
    }

    // FIXME -- this is all a terrible mixing of view and model
    // This should be calculated and accessed through results stored in Tool (which needs a refactor
    // to be a better DB)

    public CategoryResults getCommercialCategoryResults() {
        return this.commercialCategResults;
    }

    public int getCommercialToolCount() {
        return this.commercialToolCount;
    }

    public int getCommercialLow() {
        return (int) Math.round(this.commercialLow);
    }

    public TestSuiteResults.ToolType getCommercialLowToolType() {
        return this.commercialLowToolType;
    }

    public int getCommercialAve() {
        return (int) Math.round(this.commercialAveScore);
    }

    public int getCommercialHigh() {
        return (int) Math.round(this.commercialHigh);
    }

    public TestSuiteResults.ToolType getCommercialHighToolType() {
        return this.commercialHighToolType;
    }

    public CategoryResults getNonCommercialCategoryResults() {
        return this.noncommercialCategResults;
    }

    public int getNonCommercialToolCount() {
        return this.noncommercialToolCount;
    }

    public int getNonCommercialLow() {
        return (int) Math.round(this.noncommercialLow);
    }

    public TestSuiteResults.ToolType getNonCommercialLowToolType() {
        return this.noncommercialLowToolType;
    }

    public int getNonCommercialAve() {
        return (int) Math.round(this.noncommercialAveScore);
    }

    public int getNonCommercialHigh() {
        return (int) Math.round(this.noncommercialHigh);
    }

    public TestSuiteResults.ToolType getNonCommercialHighToolType() {
        return this.noncommercialHighToolType;
    }

    public CategoryResults getOverallCategoryResults() {
        return this.overallCategResults;
    }

    public int getOverallToolCount() {
        return this.overallToolCount;
    }

    public int getOverallLow() {
        return (int) Math.round(this.overallLow);
    }

    public TestSuiteResults.ToolType getOverallLowToolType() {
        return this.overallLowToolType;
    }

    public int getOverallAve() {
        return (int) Math.round(this.overallAveScore);
    }

    public int getOverallHigh() {
        return (int) Math.round(this.overallHigh);
    }

    public TestSuiteResults.ToolType getOverallHighToolType() {
        return this.overallHighToolType;
    }
}
