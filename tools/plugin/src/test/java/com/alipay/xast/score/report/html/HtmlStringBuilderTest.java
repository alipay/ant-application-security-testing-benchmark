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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HtmlStringBuilderTest {

    @Test
    void createsTable() {
        HtmlStringBuilder sb = new HtmlStringBuilder();

        String table =
                sb.beginTable("table-class")
                        .beginTr("header")
                        .th("first th", "some-th-class")
                        .th("second th")
                        .endTr()
                        .beginTr()
                        .td("first td", "some-td-class")
                        .td("second td")
                        .endTr()
                        .endTable()
                        .beginTable()
                        .endTable()
                        .toString();

        assertEquals(
                "<table class=\"table-class\">"
                        + "<tr class=\"header\">"
                        + "<th class=\"some-th-class\">first th</th>"
                        + "<th>second th</th>"
                        + "</tr>"
                        + "<tr>"
                        + "<td class=\"some-td-class\">first td</td>"
                        + "<td>second td</td>"
                        + "</tr>"
                        + "</table>"
                        + "<table>"
                        + "</table>",
                table);
    }

    @Test
    void treatsNullCssClassAsNoneForTables() {
        HtmlStringBuilder sb = new HtmlStringBuilder();

        String table =
                sb.beginTable(null)
                        .beginTr(null)
                        .th("th", null)
                        .td("td", null)
                        .endTr()
                        .endTable()
                        .toString();

        assertEquals("<table><tr><th>th</th><td>td</td></tr></table>", table);
    }

    @Test
    void createsParagraph() {
        HtmlStringBuilder sb = new HtmlStringBuilder();

        sb.p("Some paragraph");

        assertEquals("<p>Some paragraph</p>", sb.toString());
    }

    @Test
    void acceptsLongAndDoubleAsDashContent() {
        HtmlStringBuilder sb = new HtmlStringBuilder();

        String table =
                sb.beginTable()
                        .beginTr()
                        .th(123456)
                        .th(123.456)
                        .endTr()
                        .beginTr()
                        .td(123456)
                        .td(123.456)
                        .endTr()
                        .endTable()
                        .toString();

        assertEquals(
                "<table>"
                        + "<tr>"
                        + "<th>123456</th>"
                        + "<th>123.456</th>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>123456</td>"
                        + "<td>123.456</td>"
                        + "</tr>"
                        + "</table>",
                table);
    }
}
