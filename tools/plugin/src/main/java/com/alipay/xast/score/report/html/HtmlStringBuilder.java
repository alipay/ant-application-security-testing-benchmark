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

public class HtmlStringBuilder {

    private final StringBuilder sb = new StringBuilder();

    public HtmlStringBuilder beginTable() {
        sb.append("<table>");

        return this;
    }

    public HtmlStringBuilder beginTable(String cssClass) {
        if (cssClass == null) {
            return beginTable();
        }

        sb.append("<table class=\"").append(cssClass).append("\">");

        return this;
    }

    public HtmlStringBuilder beginTr() {
        sb.append("<tr>");

        return this;
    }

    public HtmlStringBuilder beginTr(String cssClass) {
        if (cssClass == null) {
            return beginTr();
        }

        sb.append("<tr class=\"").append(cssClass).append("\">");

        return this;
    }

    public HtmlStringBuilder th(String content) {
        sb.append("<th>").append(content).append("</th>");

        return this;
    }

    public HtmlStringBuilder th(long content) {
        sb.append("<th>").append(content).append("</th>");

        return this;
    }

    public HtmlStringBuilder th(double content) {
        sb.append("<th>").append(content).append("</th>");

        return this;
    }

    public HtmlStringBuilder th(String content, String cssClass) {
        if (cssClass == null) {
            return th(content);
        }

        sb.append("<th class=\"").append(cssClass).append("\">").append(content).append("</th>");

        return this;
    }

    public HtmlStringBuilder endTr() {
        sb.append("</tr>");

        return this;
    }

    public HtmlStringBuilder td(String content) {
        sb.append("<td>").append(content).append("</td>");

        return this;
    }

    public HtmlStringBuilder td(long content) {
        sb.append("<td>").append(content).append("</td>");

        return this;
    }

    public HtmlStringBuilder td(double content) {
        sb.append("<td>").append(content).append("</td>");

        return this;
    }

    public HtmlStringBuilder td(String content, String cssClass) {
        if (cssClass == null) {
            return td(content);
        }

        sb.append("<td class=\"").append(cssClass).append("\">").append(content).append("</td>");

        return this;
    }

    public HtmlStringBuilder endTable() {
        sb.append("</table>");

        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    public HtmlStringBuilder p(String content) {
        sb.append("<p>").append(content).append("</p>");

        return this;
    }
}
