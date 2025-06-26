package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.string;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字符串
// scene introduction = StringBuffer
// level = 2
// bind_url = completeness/single_app_tracing/datatype/string/Base_StringBuffer_001_T
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/string")
public class Base_StringBuffer_001_T {
    @PostMapping("Base_StringBuffer_001_T")
    public Map<String, Object> aTaintCase0153(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        StringBuffer data = new StringBuffer();
        data.append(cmd);
        try {
            Runtime.getRuntime().exec(data.toString());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
