package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.primitives;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型->long
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型->Long
// bind_url = completeness/base/object/javaNative/Base_Long_008_F/{cmd}
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_Long_008_F {
    @PostMapping("Base_Long_008_F/{cmd}")
    public Map<String, Object> aTaintCase0146(@PathVariable Long cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        Long l = 123L;
        try {
            Runtime.getRuntime().exec(String.valueOf(l));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
