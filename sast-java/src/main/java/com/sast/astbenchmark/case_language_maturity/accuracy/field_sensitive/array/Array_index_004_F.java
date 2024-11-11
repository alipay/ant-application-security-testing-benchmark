package com.sast.astbenchmark.case_language_maturity.accuracy.field_sensitive.array;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-数组索引-二维
 * Level 3+
 * Date 2024-06-28
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->域敏感->容器->数组索引-二维
// bind_url = accuracy/fieldSensitive/baseCollection/Array_index_004_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/fieldSensitive/baseCollection")
public class Array_index_004_F {
    @PostMapping("Array_index_004_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[][] arr = new String[][]{{cmd}, {"xx","aa"}, {"bar","b","cc"}};
            Runtime.getRuntime().exec(arr[1][1]);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}