package com.sast.astbenchmark.case_language_maturity.accuracy.field_sensitive.map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-Map-Field
 * Level 3
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->域敏感->容器->Map-Field
// bind_url = accuracy/fieldSensitive/baseCollection/MapField_001_T
// evaluation information end

@RestController()
@RequestMapping("accuracy/fieldSensitive/baseCollection")
public class MapField_001_T {
    @PostMapping(value = "MapField_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Map<String, String> map = new HashMap<>();
            map.put("key1",cmd);
            Runtime.getRuntime().exec(map.get("key1"));
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
