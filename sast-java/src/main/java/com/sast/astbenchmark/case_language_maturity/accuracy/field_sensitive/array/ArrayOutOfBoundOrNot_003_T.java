package com.sast.astbenchmark.case_language_maturity.accuracy.field_sensitive.array;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Introduction 域敏感-数组是否越界-ArrayList
 * Level 3+
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->域敏感->容器->数组是否越界-ArrayList
// compose = ArrayOutOfBoundOrNot_003_T.java && !ArrayOutOfBoundOrNot_004_F.java
// bind_url = accuracy/fieldSensitive/baseCollection/ArrayOutOfBoundOrNot_003_T
// assession information end
@RestController()
@RequestMapping("accuracy/fieldSensitive/baseCollection")
public class ArrayOutOfBoundOrNot_003_T {
    @PostMapping("ArrayOutOfBoundOrNot_003_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            List<String> arr = new ArrayList<String>() {{
                add("foo");
                add("xx");
                add("bar");
            }};
            arr.add(3, cmd);
            Runtime.getRuntime().exec(arr.toString());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}