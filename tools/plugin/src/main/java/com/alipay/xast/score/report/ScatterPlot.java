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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.annotations.XYShapeAnnotation;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.TextAnchor;

/**
 * This is the base plotting class used to create a scorecard page with a scorecard chart on it.
 * Various classes extend this to create the Home page, tool specific pages, and vuln specific
 * pages.
 */
public class ScatterPlot {

    // Used for % format in Bar Charts
    static final DecimalFormat TABLE_PCT_FORMAT = new DecimalFormat("0'%'");

    // Used for decimal format for scores in the Key (on the right)
    static final DecimalFormat KEY_DECIMAL_FORMAT = new DecimalFormat("#0.0");

    // This variable is directly accessed by ScatterHome.java
    JFreeChart chart = null;
    static final StandardChartTheme theme = initializeTheme();

    // These two are used in the far right of the generated charts
    static final int COLUMN_1_OFFSET = 60; // Used to line up the overall score per tool/category
    static final int COLUMN_2_OFFSET = 66; // Used to line up the (TPR - FPR) column
    static final Stroke dashed =
            new BasicStroke(
                    1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {6, 3}, 0);

    // Instance vars
    char averageLabel;

    public static StandardChartTheme initializeTheme() {
        String fontName = "Arial";
        StandardChartTheme theme =
                (StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();
        theme.setExtraLargeFont(new Font(fontName, Font.PLAIN, 24)); // title
        theme.setLargeFont(new Font(fontName, Font.PLAIN, 20)); // axis-title
        theme.setRegularFont(new Font(fontName, Font.PLAIN, 16));
        theme.setSmallFont(new Font(fontName, Font.PLAIN, 12));
        theme.setRangeGridlinePaint(Color.decode("#C0C0C0"));
        theme.setPlotBackgroundPaint(Color.white);
        theme.setChartBackgroundPaint(Color.white);
        theme.setGridBandPaint(Color.red);
        theme.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
        theme.setBarPainter(new StandardBarPainter());
        theme.setAxisLabelPaint(Color.decode("#666666"));
        return theme;
    }

    public static void initializePlot(JFreeChart chart) {
        XYPlot xyplot = chart.getXYPlot();
        NumberAxis rangeAxis = (NumberAxis) xyplot.getRangeAxis();
        NumberAxis domainAxis = (NumberAxis) xyplot.getDomainAxis();

        rangeAxis.setRange(-9.99, 109.99);
        rangeAxis.setNumberFormatOverride(TABLE_PCT_FORMAT);
        rangeAxis.setTickLabelPaint(Color.decode("#666666"));
        rangeAxis.setMinorTickCount(5);
        rangeAxis.setTickUnit(new NumberTickUnit(10));
        rangeAxis.setAxisLineVisible(true);
        rangeAxis.setMinorTickMarksVisible(true);
        rangeAxis.setTickMarksVisible(true);
        rangeAxis.setLowerMargin(10);
        rangeAxis.setUpperMargin(10);

        domainAxis.setRange(-5, 175);
        domainAxis.setNumberFormatOverride(TABLE_PCT_FORMAT);
        domainAxis.setTickLabelPaint(Color.decode("#666666"));
        domainAxis.setMinorTickCount(5);
        domainAxis.setTickUnit(new NumberTickUnit(10));
        domainAxis.setAxisLineVisible(true);
        domainAxis.setTickMarksVisible(true);
        domainAxis.setMinorTickMarksVisible(true);
        domainAxis.setLowerMargin(10);
        domainAxis.setUpperMargin(10);

        xyplot.setRangeGridlineStroke(new BasicStroke());
        xyplot.setRangeGridlinePaint(Color.lightGray);
        xyplot.setRangeMinorGridlinePaint(Color.decode("#DDDDDD"));
        xyplot.setRangeMinorGridlinesVisible(true);
        xyplot.setOutlineVisible(true);
        xyplot.setDomainGridlineStroke(new BasicStroke());
        xyplot.setDomainGridlinePaint(Color.lightGray);
        xyplot.setDomainMinorGridlinePaint(Color.decode("#DDDDDD"));
        xyplot.setDomainMinorGridlinesVisible(true);
        xyplot.getRenderer().setSeriesPaint(0, Color.decode("#4572a7"));

        chart.setTextAntiAlias(true);
        chart.setAntiAlias(true);
        chart.removeLegend();
        chart.setPadding(new RectangleInsets(20, 20, 0, 20));

        Point2D legendLocation = new Point2D.Double(101, -10);
        makeRect(xyplot, legendLocation, 120, 74, Color.WHITE);

        Point2D triangleLocation = new Point2D.Double(101, -10);
        Color grey = new Color(0.1f, 0.1f, 0.1f, 0.1f);
        makeTriangle(xyplot, triangleLocation, grey);

        makeGuessingLine(xyplot);
    }

    /**
     * Sort the comma separated elements of the supplied string.
     *
     * @param value - The string to sort.
     * @return A new comma separated string with the items in it in alphabetical order.
     */
    static String sort(String value) {
        String[] parts = value.split(",");
        Arrays.sort(parts);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            sb.append(parts[i]);
            if (i < parts.length - 1) sb.append(",");
        }
        return sb.toString();
    }

    /**
     * Add the letter, from the key on the right, next to the plot point on the chart for for each
     * tool to the supplied xyplot.
     *
     * @param map - A HashMap<Point2D, String> of plot points and the label to plot next to them.
     * @param xyplot - The chart to make the Data labels on.
     */
    void addLabelsToPlotPoints(HashMap<Point2D, String> map, XYPlot xyplot) {
        for (Entry<Point2D, String> e : map.entrySet()) {
            if (e.getValue() != null) {
                Point2D p = e.getKey();
                String label = sort(e.getValue());
                XYTextAnnotation annotation = new XYTextAnnotation(label, p.getX(), p.getY());
                annotation.setTextAnchor(
                        p.getX() < 3 ? TextAnchor.TOP_LEFT : TextAnchor.TOP_CENTER);
                annotation.setBackgroundPaint(Color.white);
                // set color of average plot point label to magenta(pink) and everything else to
                // blue
                if (label.toCharArray()[0] == averageLabel) {
                    annotation.setPaint(Color.magenta);
                } else {
                    annotation.setPaint(Color.blue);
                }
                annotation.setFont(theme.getRegularFont());
                xyplot.addAnnotation(annotation);
            }
        }
    }

    static void addLabelToKey(
            final XYPlot xyplot, final double x, final double y, final int i, final String label) {

        XYTextAnnotation stroketext1 = new XYTextAnnotation(label, x, y + i * -3.3);
        stroketext1.setTextAnchor(TextAnchor.CENTER_LEFT);
        stroketext1.setBackgroundPaint(Color.white);
        stroketext1.setPaint(Color.black);
        stroketext1.setFont(theme.getRegularFont());
        xyplot.addAnnotation(stroketext1);
    }

    /**
     * This method create an entry in the Key for a ScatterPlot.
     *
     * @param xyplot - The plot to add the entry to.
     * @param color - The desired color for this entry in the key
     * @param x - The x coordinate of the top left of the key
     * @param y - The y coordinate of the top left of the key
     * @param i - The row within the key for this entry (starting at 0)
     * @param key - The key for this entry (e.g., A, B, C)
     * @param entryName - The name of the entry to be added
     * @param tpr - The true positive rate for this entry (range 0 - 1)
     * @param fpr - The false positive rate for this entry (range 0 - 1)
     */
    static void addEntryToKey(
            final XYPlot xyplot,
            final Color color,
            final double x,
            final double y,
            final int i,
            final String key,
            final String entryName,
            final double tpr,
            final double fpr) {
        String TPRLabel = KEY_DECIMAL_FORMAT.format(tpr * 100);
        if (TPRLabel.endsWith("0"))
            TPRLabel =
                    TPRLabel.substring(
                            0, TPRLabel.length() - 2); // trim off .0 if it ends that way.
        String FPRLabel = KEY_DECIMAL_FORMAT.format(fpr * 100);
        if (FPRLabel.endsWith("0")) FPRLabel = FPRLabel.substring(0, FPRLabel.length() - 2);

        //        final String TOOL = "\u25A0 " + label + r.getToolNameAndVersion();
        final String LABEL = "\u25A0 " + key + entryName;
        XYTextAnnotation toolLabel = new XYTextAnnotation(LABEL, x, y + i * -3.3);
        toolLabel.setTextAnchor(TextAnchor.CENTER_LEFT);
        toolLabel.setBackgroundPaint(Color.white);
        toolLabel.setPaint(color);
        toolLabel.setFont(theme.getRegularFont());
        xyplot.addAnnotation(toolLabel);
        final String SCORE = Math.round((tpr - fpr) * 100) + "%";
        XYTextAnnotation scoreLabel =
                new XYTextAnnotation(SCORE, x + COLUMN_1_OFFSET, y + i * -3.3);
        scoreLabel.setTextAnchor(TextAnchor.CENTER_RIGHT);
        scoreLabel.setBackgroundPaint(Color.white);
        scoreLabel.setPaint(color);
        scoreLabel.setFont(theme.getRegularFont());
        xyplot.addAnnotation(scoreLabel);
        final String CALC = "(" + TPRLabel + "-" + FPRLabel + ")";
        XYTextAnnotation calcLabel = new XYTextAnnotation(CALC, x + COLUMN_2_OFFSET, y + i * -3.3);
        calcLabel.setTextAnchor(TextAnchor.CENTER);
        calcLabel.setBackgroundPaint(Color.white);
        calcLabel.setPaint(Color.gray);
        calcLabel.setFont(theme.getSmallFont());
        xyplot.addAnnotation(calcLabel);
    }

    /**
     * This method looks for multiple values that map to the same plot point. For any that do, it
     * creates a comma separated list for the label, and removes the individual entries.
     *
     * @param map - A HashMap<Point2D, String> of plot points and the label to plot next to them.
     */
    static void dedupifyPlotPoints(HashMap<Point2D, String> map) {
        for (Entry<Point2D, String> e1 : map.entrySet()) {
            Entry<Point2D, String> e2 = getMatchingPlotPoints(map, e1);
            while (e2 != null) {
                StringBuilder label = new StringBuilder();
                if (e1.getValue() != null) label.append(e1.getValue());
                if (e1.getValue() != null && e2.getValue() != null) label.append(",");
                if (e2.getValue() != null) label.append(e2.getValue());
                e1.setValue(label.toString());
                e2.setValue(null);
                e2 = getMatchingPlotPoints(map, e1);
            }
        }
    }

    /**
     * Returns a set of points, if any, that match the location of the provided point.
     *
     * @param map - The set of points to look through.
     * @param e1 - The one to match against.
     * @return A set of matching entry points, if any. Null otherwise.
     */
    static Entry<Point2D, String> getMatchingPlotPoints(
            HashMap<Point2D, String> map, Entry<Point2D, String> e1) {
        for (Entry<Point2D, String> e2 : map.entrySet()) {
            Double xd = Math.abs(e1.getKey().getX() - e2.getKey().getX());
            Double yd = Math.abs(e1.getKey().getY() - e2.getKey().getY());
            boolean close = xd < 1 && yd < 3;
            if (e1 != e2 && e1.getValue() != null && e2.getValue() != null && close) {
                return e2;
            }
        }
        return null;
    }

    public static void writeChartToFile(File f, JFreeChart chart, int height) throws IOException {
        FileOutputStream stream = new FileOutputStream(f);
        ChartUtils.writeChartAsPNG(stream, chart, (int) Math.round(height * 1.4), height);
        stream.close();
    }

    public void writeChartToFile(File f, int height) throws IOException {
        writeChartToFile(f, this.chart, height);
    }

    public void addGenerationDate(XYPlot xyplot) {
        // add scorecard generation date
        final String pattern = "dd MMM yyyy h:mm a";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        XYTextAnnotation gendate = new XYTextAnnotation("Scorecard Generated: " + date, 0.5, -7.5);
        gendate.setTextAnchor(TextAnchor.CENTER_LEFT);
        gendate.setBackgroundPaint(Color.white);
        gendate.setPaint(Color.red);
        gendate.setFont(theme.getRegularFont());
        xyplot.addAnnotation(gendate);
    }

    public static void makeGuessingLine(XYPlot xyplot) {
        // draw guessing line
        XYLineAnnotation guessing = new XYLineAnnotation(-5, -5, 100, 100, dashed, Color.red);
        xyplot.addAnnotation(guessing);

        XYPointerAnnotation worse =
                makePointer(80, 0, "Worse than guessing", TextAnchor.TOP_CENTER, 90);
        xyplot.addAnnotation(worse);

        XYPointerAnnotation better =
                makePointer(25, 100, "Better than guessing", TextAnchor.BOTTOM_CENTER, 270);
        xyplot.addAnnotation(better);

        XYTextAnnotation stroketext =
                new XYTextAnnotation("                     Random Guess", 88, 107);
        stroketext.setTextAnchor(TextAnchor.CENTER_RIGHT);
        stroketext.setBackgroundPaint(Color.white);
        stroketext.setPaint(Color.red);
        stroketext.setFont(theme.getRegularFont());
        xyplot.addAnnotation(stroketext);

        XYLineAnnotation strokekey = new XYLineAnnotation(58, 107, 68, 107, dashed, Color.red);
        xyplot.setBackgroundPaint(Color.white);
        xyplot.addAnnotation(strokekey);
    }

    // Note that rotation is for the entire G2D, so put in coordinates accordingly
    public static void makeOval(XYPlot xyplot, double x, double y, double w, int h, int angle) {
        x = x * Math.sqrt(2);
        Shape oval = new Ellipse2D.Double(x, y, w, h);
        Shape diag = rotate(oval, angle);
        XYShapeAnnotation area = new XYShapeAnnotation(diag, new BasicStroke(), Color.gray);
        xyplot.addAnnotation(area);
    }

    public static void makePoint(XYPlot xyplot, Point2D location, double radius, Color color) {
        double x = location.getX() - radius / 2;
        double y = location.getY() - radius / 2;
        Shape dot = new Ellipse2D.Double(x, y, radius, radius);
        XYShapeAnnotation area = new XYShapeAnnotation(dot, new BasicStroke(), color, color);
        xyplot.addAnnotation(area);
    }

    public static void makeRect(
            XYPlot xyplot, Point2D location, double height, double width, Color color) {
        Shape rect = new Rectangle2D.Double(location.getX(), location.getY(), width, height);
        XYShapeAnnotation area = new XYShapeAnnotation(rect, new BasicStroke(), color, color);
        xyplot.addAnnotation(area);
    }

    public static void makeTriangle(XYPlot xyplot, Point2D location, Color color) {
        Polygon p = new Polygon();
        p.addPoint(0, 0);
        p.addPoint(100, 0);
        p.addPoint(100, 100);
        XYShapeAnnotation area = new XYShapeAnnotation(p, new BasicStroke(), color, color);
        xyplot.addAnnotation(area);
    }

    public static XYPointerAnnotation makePointer(
            int x, int y, String msg, TextAnchor anchor, int angle) {
        XYPointerAnnotation pointer = new XYPointerAnnotation(msg, x, y, Math.toRadians(angle));
        pointer.setBackgroundPaint(Color.white);
        pointer.setTextAnchor(anchor);
        pointer.setArrowWidth(4);
        pointer.setArrowLength(8);
        pointer.setArrowPaint(Color.red);
        pointer.setLabelOffset(2);
        pointer.setPaint(Color.red);
        pointer.setFont(theme.getRegularFont());
        return pointer;
    }

    /**
     * This method creates a pointer and adds it to the supplied plot, rather than returning the new
     * pointer.
     *
     * @param plot
     * @param x - X coordinate
     * @param y - Y coordinate
     * @param msg
     * @param anchor
     * @param angle
     */
    public static void makePointer(
            XYPlot plot, int x, int y, String msg, TextAnchor anchor, int angle) {

        plot.addAnnotation(makePointer(x, y, msg, anchor, angle));
    }

    public static Shape rotate(Shape shape, int angle) {
        Rectangle bounds = shape.getBounds();
        double radians = Math.toRadians(angle);
        double anchorX = bounds.width / 2;
        double anchorY = bounds.height / 2;
        AffineTransform at = AffineTransform.getRotateInstance(radians, anchorX, anchorY);
        Shape rotated = at.createTransformedShape(shape);
        return rotated;
    }
}
