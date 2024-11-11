package com.sast.astbenchmark.case_language_maturity.completeness.object_tracing.datatype.array;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，Array中的对象为污点
 * Level 2
 * Date 2024-08-16
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->java原生对象->copyOf
// bind_url = completeness/base/object/javaNative/Base_ArrayAccess_009_T
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_ArrayAccess_009_T {
    @PostMapping(value = "Base_ArrayAccess_009_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] data = new String[]{cmd,"aa","b"};
            String[] s = Arrays.copyOf(data,3);
            Runtime.getRuntime().exec(s);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
