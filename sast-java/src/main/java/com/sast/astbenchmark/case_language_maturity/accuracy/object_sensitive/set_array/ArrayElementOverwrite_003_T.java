package com.sast.astbenchmark.case_language_maturity.accuracy.object_sensitive.set_array;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-数组元素敏感-ArrayList
 * Level 3
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->对象敏感->容器->数组元素敏感-ArrayList
// compose = ArrayElementOverwrite_003_T.java && !ArrayElementOverwrite_004_F.java
// bind_url = accuracy/fieldSensitive/baseCollection/ArrayElementOverwrite_003_T
// assession information end
@RestController()
@RequestMapping("accuracy/fieldSensitive/baseCollection")
public class ArrayElementOverwrite_003_T {
    @PostMapping("ArrayElementOverwrite_003_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            List<String> a = new ArrayList<>();
            List<String> b = a;
            a.add(cmd);
            List<String> c = b;

            Runtime.getRuntime().exec(c.get(0));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}