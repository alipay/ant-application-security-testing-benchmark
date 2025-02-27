package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.field_sensitive_one_collection.numeric_index_state_no_solver.array;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)->数组索引
 * Level 3
 * Date 2024-06-28
 */
// evaluation information start
// real case = false
// evaluation item =  准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
// bind_url = accuracy/object_field_sensitive/field_sensitive_one_collection/numeric_index_state_no_solver/array/Array_index_002_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/object_field_sensitive/field_sensitive_one_collection/numeric_index_state_no_solver/array")
public class Array_index_002_F {
    @PostMapping("Array_index_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] arr = new String[] {cmd, "xx", "bar"};
            Runtime.getRuntime().exec(arr[1]);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}