package com.sast.astbenchmark.case_language_maturity.completeness.object_tracing.datatype.string;

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
// real case = false
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->java原生对象->StringBuffer
// bind_url = completeness/base/object/javaNative/Base_StringBuffer_002_F
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
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
