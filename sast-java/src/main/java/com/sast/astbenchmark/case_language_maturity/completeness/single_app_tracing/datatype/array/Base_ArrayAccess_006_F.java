package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.array;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = ArrayAccess-element assignment
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/Base_ArrayAccess_006_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/array")
public class Base_ArrayAccess_006_F {
    @PostMapping("Base_ArrayAccess_006_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] arr = new String[] {"foo", "xx", "bar"};
            Runtime.getRuntime().exec(arr);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}