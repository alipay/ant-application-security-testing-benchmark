package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.field_sensitive.array;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->对象敏感与域敏感->区分多维字典/列表/数组的不同元素->数组索引-二维
 * Level 4
 * Date 2024-06-28
 */
// evaluation information start
// real case = false
// evaluation item =  准确度->对象敏感与域敏感->区分一维字典/列表/数组的不同元素->数组索引-二维
// bind_url = accuracy/object_field_sensitive/field_sensitive/array/Array_index_004_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/object_field_sensitive/field_sensitive/array")
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