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
 * @author David Anderson
 * @created 2021
 */
package com.alipay.xast.tools;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SimpleFileLogger implements Closeable, Logger {
    private static final ConcurrentMap<String, SimpleFileLogger> loggerMap =
            new ConcurrentHashMap<>();

    private static final Properties properties = new Properties();

    final File file;
    private PrintStream out;

    private SimpleFileLogger(File file) throws FileNotFoundException {
        this.file = file;
        out = new PrintStream(new FileOutputStream(file));
    }

    public static SimpleFileLogger getLogger(String name)
            throws FileNotFoundException, LoggerConfigurationException {
        SimpleFileLogger simpleLogger = loggerMap.get(name);
        if (simpleLogger != null) {
            return simpleLogger;
        } else {
            String filename = getFile(name);
            if (filename == null) {
                throw new LoggerConfigurationException("No file bound to logger ID: " + name);
            }
            File file = new File(getFile(name));
            SimpleFileLogger newInstance = new SimpleFileLogger(file);
            SimpleFileLogger oldInstance = loggerMap.putIfAbsent(name, newInstance);
            return oldInstance == null ? newInstance : oldInstance;
        }
    }

    public static void setFile(String key, File file) {
        properties.setProperty(key, file.getAbsolutePath());
    }

    public static String getFile(String key) {
        return properties.getProperty(key);
    }

    public void print(String message) {
        out.print(message);
    }

    public void println(String message) {
        out.println(message);
    }

    public void println() {
        out.println();
    }

    public void printf(String format, Object... args) {
        out.printf(format, args);
    }

    public void close() {
        out.close();
    }
}
