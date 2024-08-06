package com.sast.astbenchmark.cases.accuracy.fieldSensitive.baseCollection;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-数组元素敏感
 * Level X
 * Date 2024-06-28
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->域敏感->容器->数组元素敏感
// compose = ArrayElementOverwrite_001_T.java && !ArrayElementOverwrite_002_F.java
// bind_url = accuracy/fieldSensitive/baseCollection/ArrayElementOverwrite_002_F
// assession information end
@RestController()
@RequestMapping("accuracy/fieldSensitive/baseCollection")
public class ArrayElementOverwrite_002_F {
    @PostMapping("ArrayElementOverwrite_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] a = new String[2];
            String[] b = new String[2];
            a[1] = cmd;
            String[] c = b;

            Runtime.getRuntime().exec(c[1]);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}