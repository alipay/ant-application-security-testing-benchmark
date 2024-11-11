package com.sast.astbenchmark.case_language_maturity.completeness.object_tracing.datatype.string;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，String对象为污点
 * Level 2
 * Date 2024-05-09
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->java原生对象->String
// bind_url = completeness/object_tracing/datatype/string/Base_String_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/object_tracing/datatype/string")
public class Base_String_001_T {
    @PostMapping("Base_String_001_T")
    public Map<String, Object> aTaintCase0152(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
