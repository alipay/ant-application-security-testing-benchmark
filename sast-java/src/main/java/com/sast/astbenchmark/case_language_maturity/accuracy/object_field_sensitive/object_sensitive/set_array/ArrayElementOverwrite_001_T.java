package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.object_sensitive.set_array;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->对象敏感与域敏感->区分不同的类对象、结构体/联合体和字典/列表/数组->容器->数组元素敏感
 * Level 2
 * Date 2024-06-28
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感->区分不同的类对象、结构体/联合体和字典/列表/数组->容器->数组元素敏感
// bind_url = accuracy/object_field_sensitive/object_sensitive/set_array/ArrayElementOverwrite_001_T
// evaluation information end
@RestController()
@RequestMapping("accuracy/object_field_sensitive/object_sensitive/set_array")
public class ArrayElementOverwrite_001_T {
    @PostMapping("ArrayElementOverwrite_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] a = new String[2];
            String[] b = a;
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