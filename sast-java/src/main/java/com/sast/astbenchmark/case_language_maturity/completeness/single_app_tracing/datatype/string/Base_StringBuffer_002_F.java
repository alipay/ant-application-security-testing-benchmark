package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.string;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->数据类型和结构->字符串->StringBuffer
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字符串
// bind_url = completeness/single_app_tracing/datatype/string/Base_StringBuffer_002_F
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/string")
public class Base_StringBuffer_002_F {
    @PostMapping("Base_StringBuffer_002_F")
    public Map<String, Object> aTaintCase0153(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String s = "_";
        StringBuffer data = new StringBuffer();
        data.append(s);
        try {
            Runtime.getRuntime().exec(data.toString());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
