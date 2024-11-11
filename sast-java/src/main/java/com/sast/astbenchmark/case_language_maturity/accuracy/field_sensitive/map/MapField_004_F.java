package com.sast.astbenchmark.case_language_maturity.accuracy.field_sensitive.map;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-Map-Values
 * Level 3
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->域敏感->容器->Map-Values
// bind_url = accuracy/fieldSensitive/baseCollection/MapField_004_F
// evaluation information end

@RestController()
@RequestMapping("accuracy/fieldSensitive/baseCollection")
public class MapField_004_F {
    @PostMapping(value = "MapField_004_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Map<String, String> map = new HashMap<>();
            map.put("key1","_");
            SinkUtil.sink(map.values());
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
