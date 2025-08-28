package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.field_sensitive_muilt_collection;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item =  准确度->对象敏感与域敏感分析->区分多维字典/列表/数组的不同元素
// scene introduction = 数组索引-二维
// level = 4
// bind_url = accuracy/object_field_sensitive/field_sensitive_muilt_collection/Array_index_muilt_collection_001_T
// evaluation information end
@RestController()
@RequestMapping("accuracy/object_field_sensitive/field_sensitive_muilt_collection")
public class Array_index_muilt_collection_001_T {
    @PostMapping("Array_index_muilt_collection_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[][] arr = new String[][] {{cmd}, {"xx", "aa"}, {"bar", "b", "cc"}};
            Runtime.getRuntime().exec(arr[0][0]);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
