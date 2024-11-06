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
package com.alipay.xast.score.report;

import static com.alipay.xast.score.report.Formats.fourDecimalPlacesNumber;
import static com.alipay.xast.score.report.Formats.twoDecimalPlacesPercentage;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FormatsTest {

    @Test
    void hasFormatterForTwoDecimalPlacesPercentage() {
        assertEquals("1234.57%", twoDecimalPlacesPercentage.format(12.345678));
    }

    @Test
    void hasFormatterForFourDecimalPlaces() {
        assertEquals("12.3457", fourDecimalPlacesNumber.format(12.345678));
    }
}
