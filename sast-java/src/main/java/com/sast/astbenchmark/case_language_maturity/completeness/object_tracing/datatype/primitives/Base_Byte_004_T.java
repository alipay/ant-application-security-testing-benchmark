package com.sast.astbenchmark.case_language_maturity.completeness.object_tracing.datatype.primitives;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，Byte作为污点
 * Level 2
 * Date 2024-05-09
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->java原生对象->Byte
// bind_url = completeness/object_tracing/datatype/primitives/Base_Byte_004_T/{cmd}
// evaluation information end
@RestController()
@RequestMapping("completeness/object_tracing/datatype/primitives")
public class Base_Byte_004_T {
    @PostMapping("Base_Byte_004_T/{cmd}")
    public Map<String, Object> aTaintCase0144_2(@PathVariable Byte cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
