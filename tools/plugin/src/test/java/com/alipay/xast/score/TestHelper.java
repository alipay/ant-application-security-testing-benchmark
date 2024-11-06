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
 * @created 2022
 */
package com.alipay.xast.score;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;
import org.apache.commons.io.IOUtils;

public class TestHelper {

    public static ResultFile resultFileOf(String filename) {
        try {
            return new ResultFile(filename, contentOf(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] contentOf(String filename) {
        try {
            return IOUtils.toByteArray(asStream(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultFile resultFileWithoutLineBreaksOf(String filename) {
        try {
            return new ResultFile(filename, contentWithoutLineBreaksOf(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String contentWithoutLineBreaksOf(String filename) {
        try {
            return IOUtils.toString(asStream(filename), Charset.defaultCharset())
                    .replace('\n', ' ');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static InputStream asStream(String filename) {
        InputStream stream = TestHelper.class.getClassLoader().getResourceAsStream(filename);
        if (stream == null) {
            System.out.println("TEST ERROR: Test file: " + filename + " does not exist");
        }
        return Objects.requireNonNull(stream);
    }
}
