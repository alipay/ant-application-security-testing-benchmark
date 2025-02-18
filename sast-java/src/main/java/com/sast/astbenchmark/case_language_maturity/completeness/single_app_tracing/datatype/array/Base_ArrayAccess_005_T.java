package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.array;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->数据类型和结构->数组->ArrayAccess-element assignment
 * Level 2
 * Date 2024-06-28
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// bind_url = completeness/single_app_tracing/datatype/array/Base_ArrayAccess_005_T
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/array")
public class Base_ArrayAccess_005_T {
    @PostMapping("Base_ArrayAccess_005_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] arr = new String[]{"foo", "xx", "bar"};
            arr[1] = cmd;
            Runtime.getRuntime().exec(arr);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}