package com.sast.astbenchmark.case_language_maturity.completeness.object_tracing.datatype.array;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，多维数组中的对象为污点
 * Level 2
 * Date 2024-08-16
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->java原生对象->多维数组
// bind_url = completeness/object_tracing/datatype/array/Base_ArrayAccess_005_T
// evaluation information end
@RestController()
@RequestMapping("completeness/object_tracing/datatype/array")
public class Base_ArrayAccess_005_T {
    @PostMapping(value = "Base_ArrayAccess_005_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[][] data = new String[1][1];
            data[0][0] = cmd;
            Runtime.getRuntime().exec(data[0][0]);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
