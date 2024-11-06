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

import static java.io.File.createTempFile;
import static org.apache.commons.lang.math.RandomUtils.nextBoolean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class ConfigurationTest {

    private Map<String, Object> defaultConfig;
    private Yaml yaml;
    private final ClassLoader classLoader = Configuration.class.getClassLoader();
    private ByteArrayOutputStream out;
    private static final String SEP = System.getProperty("line.separator");

    @BeforeEach
    public void setUp() {
        // Prevent JSON-like output (https://stackoverflow.com/a/62305688)
        final DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);

        yaml = new Yaml(options);
        defaultConfig = yaml.load(classLoader.getResourceAsStream(Configuration.DEFAULT_CONFIG));

        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void canReadConfigFromDefaultFile() {
        assertConfigEquals(defaultConfig, Configuration.fromDefaultConfig());
    }

    private void assertConfigEquals(
            Map<String, Object> expectedConfig, Configuration actualConfig) {
        assertEquals(expectedConfig.get("expectedresults"), actualConfig.expectedResultsFileName);
        assertEquals(expectedConfig.get("resultsfileordir"), actualConfig.resultsFileOrDirName);
        assertEquals(expectedConfig.get("focustool"), actualConfig.focus);
        assertEquals(expectedConfig.get("anonymousmode"), actualConfig.anonymousMode);
        assertEquals(expectedConfig.get("averageonlymode"), actualConfig.showAveOnlyMode);
        assertEquals(expectedConfig.get("mixedmode"), actualConfig.mixedMode);
        assertEquals(expectedConfig.get("cwecategoryname"), actualConfig.cweCategoryName);
        assertEquals(expectedConfig.get("tprlabel"), actualConfig.tprLabel);
        assertEquals(expectedConfig.get("includeprojectlink"), actualConfig.includeProjectLink);
        assertEquals(expectedConfig.get("includeprecision"), actualConfig.includePrecision);
    }

    @Test
    void informsAboutDefaultConfig() {
        Configuration.fromDefaultConfig();
        assertEquals(
                "INFO: Default YAML Scoring config file found and loaded." + SEP, out.toString());
    }

    @Test
    void canReadConfigFromFileByResource() {
        assertConfigEquals(
                defaultConfig, Configuration.fromResourceFile(Configuration.DEFAULT_CONFIG));
    }

    @Test
    void informsAboutResourceConfig() {
        Configuration.fromResourceFile(Configuration.DEFAULT_CONFIG);

        assertEquals("INFO: YAML Scoring config file found and loaded." + SEP, out.toString());
    }

    @Test
    void throwsExceptionIfResourceFileDoesNotExist() {
        try {
            Configuration.fromResourceFile("does-not-exist.yaml");

            fail("No exception was thrown");
        } catch (Configuration.ConfigCouldNotBeParsed e) {
            assertEquals(
                    "YAML scoring configuration file: 'does-not-exist.yaml' not found on classpath!",
                    e.getMessage());
        }
    }

    @Test
    void canReadConfigFromFileByPath() throws IOException {
        Map<String, Object> testConfig = new HashMap<>();
        testConfig.put("expectedresults", randomString());
        testConfig.put("resultsfileordir", randomString());
        testConfig.put("focustool", randomString());
        testConfig.put("anonymousmode", randomBoolean());
        testConfig.put("averageonlymode", randomBoolean());
        testConfig.put("mixedmode", randomBoolean());
        testConfig.put("cwecategoryname", randomString());
        testConfig.put("tprlabel", randomString());
        testConfig.put("includeprojectlink", randomBoolean());
        testConfig.put("includeprecision", randomBoolean());

        File tempConfigFile = createTempFile("config", ".yaml");

        try (FileWriter writer = new FileWriter(tempConfigFile)) {
            yaml.dump(testConfig, writer);
        }

        assertConfigEquals(testConfig, Configuration.fromFile(tempConfigFile.getAbsolutePath()));
    }

    private String randomString() {
        return UUID.randomUUID().toString();
    }

    private Boolean randomBoolean() {
        return nextBoolean();
    }

    @Test
    void informsAboutFileConfig() throws Exception {
        Configuration.fromFile(provideEmptyConfig().getAbsolutePath());

        assertEquals("INFO: YAML Scoring config file found and loaded." + SEP, out.toString());
    }

    @Test
    void throwsExceptionIfFileDoesNotExist() {
        try {
            Configuration.fromFile("does-not-exist.yaml");

            fail("No exception was thrown");
        } catch (Configuration.ConfigCouldNotBeParsed e) {
            assertEquals(
                    "YAML scoring configuration file: 'does-not-exist.yaml' not found!",
                    e.getMessage());
        }
    }

    @Test
    void mergesWithDefaultConfig() throws IOException {
        Map<String, Object> testConfig = new HashMap<>();

        testConfig.put("expectedresults", randomString());
        testConfig.put("focustool", randomString());
        testConfig.put("averageonlymode", !((Boolean) defaultConfig.get("averageonlymode")));
        testConfig.put("cwecategoryname", randomString());
        testConfig.put("includeprojectlink", !((Boolean) defaultConfig.get("includeprojectlink")));

        File tempConfigFile = createTempFile("config", ".yaml");

        try (FileWriter writer = new FileWriter(tempConfigFile)) {
            yaml.dump(testConfig, writer);
        }

        Configuration actualConfig = Configuration.fromFile(tempConfigFile.getAbsolutePath());

        assertEquals(testConfig.get("expectedresults"), actualConfig.expectedResultsFileName);
        assertEquals(defaultConfig.get("resultsfileordir"), actualConfig.resultsFileOrDirName);
        assertEquals(testConfig.get("focustool"), actualConfig.focus);
        assertEquals(defaultConfig.get("anonymousmode"), actualConfig.anonymousMode);
        assertEquals(testConfig.get("averageonlymode"), actualConfig.showAveOnlyMode);
        assertEquals(defaultConfig.get("mixedmode"), actualConfig.mixedMode);
        assertEquals(testConfig.get("cwecategoryname"), actualConfig.cweCategoryName);
        assertEquals(defaultConfig.get("tprlabel"), actualConfig.tprLabel);
        assertEquals(testConfig.get("includeprojectlink"), actualConfig.includeProjectLink);
        assertEquals(defaultConfig.get("includeprecision"), actualConfig.includePrecision);
    }

    @Test
    void doesNotFailMergingOnAnyMissingField() throws IOException {
        assertConfigEquals(
                defaultConfig, Configuration.fromFile(provideEmptyConfig().getAbsolutePath()));
    }

    private File provideEmptyConfig() throws IOException {
        // Using config with dummy value (otherwise there'll be an exception)
        File tempConfigFile = createTempFile("config", ".yaml");
        HashMap<String, Object> someConfig = new HashMap<>();
        someConfig.put("something", "value");

        try (FileWriter writer = new FileWriter(tempConfigFile)) {
            yaml.dump(someConfig, writer);
        }
        return tempConfigFile;
    }
}
