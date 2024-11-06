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
 * @created 2023
 */
package com.alipay.xast.score;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BenchmarkScoreTest {

    private static final String SEP = System.getProperty("line.separator");
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void usesDefaultConfigAndInformsAboutUsageOnNullArgs() {
        BenchmarkScore.loadConfigFromCommandLineArguments(null);

        expectDefaultConfigAndUsageMessage();
    }

    private void expectDefaultConfigAndUsageMessage() {
        String[] resultLines = outContent.toString().split(SEP);

        assertEquals(2, resultLines.length);
        assertEquals(BenchmarkScore.USAGE_MSG, resultLines[0]);
        assertEquals(Configuration.DEFAULT_SUCCESS_MESSAGE, resultLines[1]);
    }

    @Test
    void usesDefaultConfigAndInformsAboutUsageOnEmptyArgs() {
        BenchmarkScore.loadConfigFromCommandLineArguments(new String[0]);

        expectDefaultConfigAndUsageMessage();
    }

    @Test
    void usesDefaultConfigAndInformsAboutUsageOnSingleElementArgs() {
        BenchmarkScore.loadConfigFromCommandLineArguments(new String[] {"a"});

        expectDefaultConfigAndUsageMessage();
    }

    @Test
    void usesDefaultConfigAndInformsAboutUsageOnMultiElementsArgs() {
        BenchmarkScore.loadConfigFromCommandLineArguments(new String[] {"a", "b", "c"});

        expectDefaultConfigAndUsageMessage();
    }

    // @Test
    // void usesDefaultConfigAndInformsAboutUsageOnTwoElementsNullArgs() {
    //    BenchmarkScore.loadConfigFromCommandLineArguments(new String[] {null, null});
    //
    //    expectDefaultConfigAndUsageMessage();
    // }

    @Test
    void throwsExceptionAndInformsAboutUsageOnTwoElementsArrayFirstNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> BenchmarkScore.loadConfigFromCommandLineArguments(new String[] {"a", null}));

        expectUsageMessage();
    }

    private void expectUsageMessage() {
        String[] resultLines = outContent.toString().split(SEP);

        assertEquals(1, resultLines.length);
        assertEquals(BenchmarkScore.USAGE_MSG, resultLines[0]);
    }

    @Test
    void throwsExceptionAndInformsAboutUsageOnTwoElementsArraySecondNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> BenchmarkScore.loadConfigFromCommandLineArguments(new String[] {null, "b"}));

        expectUsageMessage();
    }
}
