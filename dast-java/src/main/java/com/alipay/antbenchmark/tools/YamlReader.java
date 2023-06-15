package com.alipay.antbenchmark.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * yaml reader
 */
public class YamlReader {

    private static final Logger log = LoggerFactory.getLogger("YamlReader");

    /**
     * yaml get Map
     */
    public static Map getYamlValue(String fileName) {
        log.info("getYamlValue . the fileName:{}", fileName);
        Yaml yaml = new Yaml();
        try {
            InputStream input = new FileInputStream(fileName);
            Map<String, String> yamlMap = yaml.load(input);
            if (!CollectionUtils.isEmpty(yamlMap)) {
                return yamlMap;
            }
        } catch (Exception e) {
            log.error("getYamlValue error . the exception:{}", e.getMessage());
        }
        return null;
    }
}
