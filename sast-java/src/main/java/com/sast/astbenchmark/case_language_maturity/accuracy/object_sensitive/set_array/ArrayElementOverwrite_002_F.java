package com.sast.astbenchmark.case_language_maturity.accuracy.object_sensitive.set_array;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-数组元素敏感
 * Level 3
 * Date 2024-06-28
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感->容器->数组元素敏感
// bind_url = accuracy/objectSensitive/baseCollection/ArrayElementOverwrite_002_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/objectSensitive/baseCollection")
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