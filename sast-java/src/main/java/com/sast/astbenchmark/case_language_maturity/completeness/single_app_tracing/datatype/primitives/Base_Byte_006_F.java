package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.primitives;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，byte作为污点
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->java原生对象->byte
// bind_url = completeness/base/object/javaNative/Base_Byte_006_F/{cmd}
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_Byte_006_F {
    @GetMapping("Base_Byte_006_F/{cmd}")
    public Map<String, Object> aTaintCase0138_2(@PathVariable byte cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        byte b = 0;
        try {
            Runtime.getRuntime().exec(String.valueOf(b));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

}
