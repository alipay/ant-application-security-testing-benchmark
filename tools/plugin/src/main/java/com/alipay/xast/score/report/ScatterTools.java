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

import com.alipay.xast.score.CategoryResults;
import com.alipay.xast.score.ToolResults;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ScatterTools extends ScatterPlot {

    private double atpr, afpr;

    public ScatterTools(String title, int height, ToolResults toolResults) {
        display("          " + title, height, toolResults);
    }

    /**
     * Generate a 'Scatter' chart for one tool and return the created chart.
     *
     * @param title - Title of chart being created. This is included at top of generated chat.
     * @param height - The height of the chart to create. Width is a fixed ratio of height.
     * @param toolResults - The scores for this tool.
     * @return The generated scatter chart for this tool's results.
     */
    private JFreeChart display(String title, int height, ToolResults toolResults) {

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Scores");
        int totalTools = 0;
        double totalToolTPR = 0;
        double totalToolFPR = 0;
        for (CategoryResults r : toolResults.getCategoryResults()) {
            series.add(r.falsePositiveRate * 100, r.truePositiveRate * 100);
            totalTools++;
            totalToolTPR += r.truePositiveRate;
            totalToolFPR += r.falsePositiveRate;
        }
        atpr = totalToolTPR / totalTools;
        afpr = totalToolFPR / totalTools;

        if (toolResults.getCategoryResults().size() > 1) {
            series.add(afpr * 100, atpr * 100);
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

        makeDataLabels(toolResults, xyplot);
        makeLegend(toolResults, 103, 93, dataset, xyplot);

        // TODO: Make this into a method, or add it to makeDataLabels
        for (XYDataItem item : (List<XYDataItem>) series.getItems()) {
            double x = item.getX().doubleValue();
            double y = item.getY().doubleValue();
            double z = (x + y) / 2;
            XYLineAnnotation score = new XYLineAnnotation(x, y, z, z, dashed, Color.blue);
            xyplot.addAnnotation(score);
        }

        XYTextAnnotation time =
                new XYTextAnnotation("Tool run time: " + toolResults.getScanTime(), 12, -5.6);
        time.setTextAnchor(TextAnchor.TOP_LEFT);
        time.setFont(theme.getRegularFont());
        time.setPaint(Color.red);
        xyplot.addAnnotation(time);

        return this.chart;
    }

    /**
     * Add the letter, from the key on the right, next to the plot point on the chart for for each
     * tool to the supplied xyplot.
     *
     * @param tools - THe set of tool results.
     * @param xyplot - The chart to make the Data labels on.
     */
    private void makeDataLabels(ToolResults toolResults, XYPlot xyplot) {
        HashMap<Point2D, String> map = makePointList(toolResults);
        addLabelsToPlotPoints(map, xyplot);
    }

    private SecureRandom sr = new SecureRandom();

    private HashMap<Point2D, String> makePointList(ToolResults toolResults) {
        HashMap<Point2D, String> map = new HashMap<Point2D, String>();
        char ch = ScatterHome.INITIAL_LABEL;
        int size = 0;
        // make a list of all points. Add in a tiny random to prevent exact
        // duplicate coordinates in map
        for (CategoryResults r : toolResults.getCategoryResults()) {
            size++;
            double x = r.falsePositiveRate * 100 + sr.nextDouble() * .000001;
            // this puts the label just below the point
            double y = r.truePositiveRate * 100 + sr.nextDouble() * .000001 - 1;
            Point2D p = new Point2D.Double(x, y);
            String label = "" + ch;
            map.put(p, label);
            // Weak hack if there are more than 26 tools scored. This will only get us to 52.
            if (ch == 'Z') ch = 'a';
            else ch++;
        }
        // add average point
        if (size > 1) {
            double x = afpr * 100 + sr.nextDouble() * .000001;
            double y = atpr * 100 + sr.nextDouble() * .000001 - 1;
            Point2D p = new Point2D.Double(x, y);
            String label = "" + ch;
            this.averageLabel = ch;
            map.put(p, label);
        }
        dedupifyPlotPoints(map);
        return map;
    }

    private void makeLegend(
            ToolResults or, double x, double y, XYSeriesCollection dataset, XYPlot xyplot) {
        char ch = ScatterHome.INITIAL_LABEL;
        int i = 0;
        int toolCount = 0;
        double totalScore = 0;
        double totalTPR = 0;
        double totalFPR = 0;
        final DecimalFormat DF = new DecimalFormat("#0.0");
        for (CategoryResults r : or.getCategoryResults()) {
            toolCount++;
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
                    r.category,
                    r.truePositiveRate,
                    r.falsePositiveRate);

            double score = 100 * (r.truePositiveRate - r.falsePositiveRate);
            totalScore += score; // Already multiplied by 100
            totalTPR += r.truePositiveRate; // From 0-1, additive
            totalFPR += r.falsePositiveRate; // From 0-1, additive;
            i++;
            // Weak hack if there are more than 26 tools scored. This will only get us to 52.
            if (ch == 'Z') ch = 'a';
            else ch++;
        }

        if (toolCount > 1) {

            addEntryToKey(
                    xyplot,
                    Color.magenta,
                    x,
                    y,
                    i,
                    ch + ": ",
                    "Average Score for this Tool",
                    totalTPR / toolCount,
                    totalFPR / toolCount);

            Point2D averagePoint = new Point2D.Double(afpr * 100, atpr * 100);
            makePoint(xyplot, averagePoint, 3, Color.magenta);
        }
    }
}
