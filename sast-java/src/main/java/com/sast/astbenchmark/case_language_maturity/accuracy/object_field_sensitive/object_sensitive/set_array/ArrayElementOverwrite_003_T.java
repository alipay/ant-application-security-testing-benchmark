package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.object_sensitive.set_array;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Introduction 准确度->对象敏感与域敏感->区分不同的类对象、结构体/联合体和字典/列表/数组->容器->数组元素敏感-ArrayList
 * Level 2
 * Date 2024-08-16
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感->区分不同的类对象、结构体/联合体和字典/列表/数组
// bind_url = accuracy/object_field_sensitive/object_sensitive/set_array/ArrayElementOverwrite_003_T
// evaluation information end
@RestController()
@RequestMapping("accuracy/object_field_sensitive/object_sensitive/set_array")
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