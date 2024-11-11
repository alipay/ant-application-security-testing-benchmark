package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.string;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，StringBuffer对象为污点
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->java原生对象->StringBuffer
// bind_url = completeness/base/object/javaNative/Base_StringBuffer_001_T
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
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
