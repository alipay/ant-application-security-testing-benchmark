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

public class ScatterHome extends ScatterPlot {

    private double commercialAveFPR = 0; // The average FPR for commercial tools.
    private double commercialAveTPR = 0; // The average TPR. These are range 0-1.
    private final String focus;
    static final char INITIAL_LABEL = 'A';

    /**
     * This calculates the summary chart across all the tools analyzed against a test suite.
     *
     * @param title - The title of the chart to be produced.
     * @param tools - The set of all tools. Each includes that tool's scoring results.
     * @param focus - The toolname to focus on. Blank or null if no tool should be emphasized.
     */
    public ScatterHome(String title, Set<Tool> tools, String focus) {
        this.focus = focus;
        display("          " + title, tools);
    }

    private JFreeChart display(String title, Set<Tool> tools) {

        // averages
        ArrayList<Double> averageCommercialFalseRates = new ArrayList<Double>();
        ArrayList<Double> averageCommercialTrueRates = new ArrayList<Double>();

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Scores");
        for (Tool toolReport : tools) {
            if (!toolReport.isCommercial()) {
                ToolResults overallResults = toolReport.getOverallResults();
                series.add(
                        overallResults.getFalsePositiveRate() * 100,
                        overallResults.getTruePositiveRate() * 100);
                if (toolReport.isCommercial()) {
                    averageCommercialFalseRates.add(overallResults.getFalsePositiveRate());
                    averageCommercialTrueRates.add(overallResults.getTruePositiveRate());
                }
            }
        }

        int commercialToolCount = 0;
        for (Tool toolReport : tools) {
            if (toolReport.isCommercial()) {
                commercialToolCount++;
                ToolResults overallResults = toolReport.getOverallResults();
                if (!BenchmarkScore.config.showAveOnlyMode) {
                    series.add(
                            overallResults.getFalsePositiveRate() * 100,
                            overallResults.getTruePositiveRate() * 100);
                }
                if (toolReport.isCommercial()) {
                    averageCommercialFalseRates.add(overallResults.getFalsePositiveRate());
                    averageCommercialTrueRates.add(overallResults.getTruePositiveRate());
                }
            }
        }

        for (double d : averageCommercialFalseRates) {
            this.commercialAveFPR += d;
        }
        this.commercialAveFPR = this.commercialAveFPR / averageCommercialFalseRates.size();

        for (double d : averageCommercialTrueRates) {
            this.commercialAveTPR += d;
        }
        this.commercialAveTPR = this.commercialAveTPR / averageCommercialTrueRates.size();

        if (commercialToolCount > 1
                || (BenchmarkScore.config.showAveOnlyMode && commercialToolCount == 1)) {
            series.add(commercialAveFPR * 100, commercialAveTPR * 100);
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
        theme.apply(this.chart);
        initializePlot(this.chart);

        XYPlot xyplot = this.chart.getXYPlot();
        addGenerationDate(xyplot);

        // List the Key value (i.e., A, B, C) next to each plot point.
        makeDataLabels(tools, xyplot);
        // List all the tools on the right, along with their scores
        makeLegend(tools, 103, 100.5, dataset, xyplot);

        // Create the dashed lines from the baseline 0 axis line to the plotted score dot on the
        // chart for each tool plotted
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
    private void makeDataLabels(Set<Tool> tools, XYPlot xyplot) {
        HashMap<Point2D, String> map = makePointList(tools);
        addLabelsToPlotPoints(map, xyplot);
    }

    private static SecureRandom sr = new SecureRandom();
    // This method generates all the points put on the home page chart. One per tool.
    private HashMap<Point2D, String> makePointList(Set<Tool> tools) {
        HashMap<Point2D, String> map = new HashMap<Point2D, String>();
        char ch = INITIAL_LABEL;

        // make a list of all points.  Add in a tiny random to prevent exact duplicate coordinates
        // in map
        int commercialToolCount = 0;
        for (Tool r : tools) {
            if (!r.isCommercial()) {
                ToolResults or = r.getOverallResults();
                double x = or.getFalsePositiveRate() * 100 + sr.nextDouble() * .000001;
                double y =
                        or.getTruePositiveRate() * 100
                                + sr.nextDouble() * .000001
                                - 1; // this puts the label just below the point
                Point2D p = new Point2D.Double(x, y);
                String label = "" + ch;
                map.put(p, label);
                // Weak hack if there are more than 26 tools scored. This will only get us to 52.
                if (ch == 'Z') ch = 'a';
                else ch++;
            }
        }

        for (Tool r : tools) {
            if (r.isCommercial()) {
                commercialToolCount++;
                if (!BenchmarkScore.config.showAveOnlyMode) {
                    ToolResults or = r.getOverallResults();
                    double x = or.getFalsePositiveRate() * 100 + sr.nextDouble() * .000001;
                    double y =
                            or.getTruePositiveRate() * 100
                                    + sr.nextDouble() * .000001
                                    - 1; // this puts the label just below the point
                    Point2D p = new Point2D.Double(x, y);
                    String label = "" + ch;
                    map.put(p, label);
                    // Weak hack if there are more than 26 tools scored. This will only get us to
                    // 52.
                    if (ch == 'Z') ch = 'a';
                    else ch++;
                }
            }
        }

        if (commercialToolCount > 1
                || (BenchmarkScore.config.showAveOnlyMode && commercialToolCount == 1)) {
            Point2D ap =
                    new Point2D.Double(
                            commercialAveFPR * 100 + sr.nextDouble() * .000001,
                            commercialAveTPR * 100 + sr.nextDouble() * .000001 - 1);
            this.averageLabel = ch;
            map.put(ap, "" + ch);
        }

        dedupifyPlotPoints(map);
        return map;
    }

    private void makeLegend(
            Set<Tool> tools, double x, double y, XYSeriesCollection dataset, XYPlot xyplot) {
        // The first label in the Key with all the tools processed by this scorecard
        char ch = INITIAL_LABEL;
        int i = -2; // Used to keep track of which row in the key we are processing. Helps calculate
        // the Y axis location where to put the Key entry

        boolean printedNonCommercialLabel = false;

        // non-commercial results
        for (Tool r : tools) {
            ToolResults or = r.getOverallResults();
            if (!r.isCommercial()) {
                // print non-commercial label if there is at least one non-commercial tool
                if (!printedNonCommercialLabel) {
                    addLabelToKey(xyplot, x, y, i, "Non-Commercial");
                    i++;
                    printedNonCommercialLabel = true;
                }

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
                        r.getToolNameAndVersion(),
                        or.getTruePositiveRate(),
                        or.getFalsePositiveRate());
                i++;
                // Weak hack if there are more than 26 tools scored. This will only get us to 52.
                if (ch == 'Z') ch = 'a';
                else ch++;
            }
        }

        // commercial tools - Their averages have already been calculated
        boolean printedCommercialLabel = false;
        int commercialToolCount = 0;
        final DecimalFormat DF = new DecimalFormat("#0.0");

        for (Tool r : tools) {

            ToolResults or = r.getOverallResults();
            if (r.isCommercial()) {

                // print commercial label if there is at least one commercial tool
                if (!printedCommercialLabel) {
                    addLabelToKey(xyplot, x, y, i, "Commercial");
                    i++;
                    printedCommercialLabel = true;
                }

                commercialToolCount++;
                // Special hack to make it line up better if the letter is an 'I' or 'i'
                String label = (ch == 'I' || ch == 'i' ? ch + ":   " : ch + ": ");
                // Another hack to make it line up better if the letter is a 'J' or 'j'
                label = (ch == 'J' || ch == 'j' ? ch + ":  " : label);
                if (!BenchmarkScore.config.showAveOnlyMode) {
                    addEntryToKey(
                            xyplot,
                            Color.blue,
                            x,
                            y,
                            i,
                            label,
                            r.getToolNameAndVersion(),
                            or.getTruePositiveRate(),
                            or.getFalsePositiveRate());
                    i++;
                    // Weak hack if more than 26 tools scored. This will only get us to 52.
                    if (ch == 'Z') ch = 'a';
                    else ch++;
                }
            }

            // This highlights the tool of focus, making that plot point green. Rarely used.
            if (r.getToolName().replace(' ', '_').equalsIgnoreCase(focus)) {
                ToolResults orc = r.getOverallResults();
                Point2D focusPoint =
                        new Point2D.Double(
                                orc.getFalsePositiveRate() * 100, orc.getTruePositiveRate() * 100);
                Color green = new Color(0, 1, 0, 0.5f);
                makePoint(xyplot, focusPoint, 3, green);
            }
        }

        // commercial average
        if (commercialToolCount > 1
                || (BenchmarkScore.config.showAveOnlyMode && commercialToolCount == 1)) {

            addEntryToKey(
                    xyplot,
                    Color.magenta,
                    x,
                    y,
                    i,
                    ch + ": ",
                    "Commercial Average",
                    commercialAveTPR,
                    commercialAveFPR);

            Point2D averagePoint =
                    new Point2D.Double(this.commercialAveFPR * 100, this.commercialAveTPR * 100);
            makePoint(xyplot, averagePoint, 3, Color.magenta);
        }
    }

    public static void generateComparisonChart(Set<Tool> tools, String focus, File scoreCardDir) {
        try {
            String scatterTitle =
                    BenchmarkScore.fullTestSuiteName(BenchmarkScore.TESTSUITE)
                            + (BenchmarkScore.config.mixedMode
                                    ? ""
                                    : " v" + BenchmarkScore.TESTSUITEVERSION)
                            + " Results Comparison";
            ScatterHome scatter = new ScatterHome(scatterTitle, tools, focus);
            scatter.writeChartToFile(new File(scoreCardDir, "toolresults_comparison.png"), 800);
        } catch (IOException e) {
            System.out.println("Couldn't generate tool results comparison chart for some reason.");
            e.printStackTrace();
        }
    }
}
