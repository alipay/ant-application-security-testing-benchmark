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
package com.alipay.xast.score.builder;

import static java.io.File.createTempFile;

import com.alipay.xast.score.Configuration;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class ConfigurationBuilder {

    private String expectedResultsFileName;
    private String resultsFileOrDirName;
    private String focus;
    private boolean anonymousMode;
    private boolean includePrecision;
    private boolean showAveOnlyMode;
    private boolean mixedMode;
    private String cweCategoryName;
    private String tprLabel;
    private boolean includeProjectLink;

    private ConfigurationBuilder() {
        Configuration defaultConfig = Configuration.fromDefaultConfig();

        this.expectedResultsFileName = defaultConfig.expectedResultsFileName;
        this.resultsFileOrDirName = defaultConfig.resultsFileOrDirName;
        this.focus = defaultConfig.focus;
        this.anonymousMode = defaultConfig.anonymousMode;
        this.includePrecision = defaultConfig.includePrecision;
        this.showAveOnlyMode = defaultConfig.showAveOnlyMode;
        this.mixedMode = defaultConfig.mixedMode;
        this.cweCategoryName = defaultConfig.cweCategoryName;
        this.tprLabel = defaultConfig.tprLabel;
        this.includeProjectLink = defaultConfig.includeProjectLink;
    }

    public static ConfigurationBuilder builder() {
        return new ConfigurationBuilder();
    }

    public ConfigurationBuilder setExpectedResultsFileName(String expectedResultsFileName) {
        this.expectedResultsFileName = expectedResultsFileName;

        return this;
    }

    public ConfigurationBuilder setResultsFileOrDirName(String resultsFileOrDirName) {
        this.resultsFileOrDirName = resultsFileOrDirName;

        return this;
    }

    public ConfigurationBuilder setFocus(String focus) {
        this.focus = focus;

        return this;
    }

    public ConfigurationBuilder setAnonymousMode(boolean anonymousMode) {
        this.anonymousMode = anonymousMode;

        return this;
    }

    public ConfigurationBuilder setIncludePrecision(boolean includePrecision) {
        this.includePrecision = includePrecision;

        return this;
    }

    public ConfigurationBuilder setShowAveOnlyMode(boolean showAveOnlyMode) {
        this.showAveOnlyMode = showAveOnlyMode;

        return this;
    }

    public ConfigurationBuilder setMixedMode(boolean mixedMode) {
        this.mixedMode = mixedMode;

        return this;
    }

    public ConfigurationBuilder setCweCategoryName(String cweCategoryName) {
        this.cweCategoryName = cweCategoryName;

        return this;
    }

    public ConfigurationBuilder setTprLabel(String tprLabel) {
        this.tprLabel = tprLabel;

        return this;
    }

    public ConfigurationBuilder setIncludeProjectLink(boolean includeProjectLink) {
        this.includeProjectLink = includeProjectLink;

        return this;
    }

    public Configuration build() {
        try {
            Map<String, Object> testConfig = new HashMap<>();

            testConfig.put("expectedresults", expectedResultsFileName);
            testConfig.put("resultsfileordir", resultsFileOrDirName);
            testConfig.put("focustool", focus);
            testConfig.put("anonymousmode", anonymousMode);
            testConfig.put("averageonlymode", showAveOnlyMode);
            testConfig.put("mixedmode", mixedMode);
            testConfig.put("cwecategoryname", cweCategoryName);
            testConfig.put("tprlabel", tprLabel);
            testConfig.put("includeprojectlink", includeProjectLink);
            testConfig.put("includeprecision", includePrecision);

            return Configuration.fromFile(writeTempConfig(testConfig).getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static File writeTempConfig(Map<String, Object> testConfig) throws IOException {
        Yaml yaml = getYaml();

        File tempConfigFile = createTempFile("config", ".yaml");

        try (FileWriter writer = new FileWriter(tempConfigFile)) {
            yaml.dump(testConfig, writer);
        }
        return tempConfigFile;
    }

    private static Yaml getYaml() {
        final DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);

        Yaml yaml = new Yaml(options);
        return yaml;
    }
}
