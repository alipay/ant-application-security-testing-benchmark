package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.string;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->数据类型和结构->字符串->String
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字符串->String
// bind_url = completeness/base/object/javaNative/Base_String_001_T
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
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
