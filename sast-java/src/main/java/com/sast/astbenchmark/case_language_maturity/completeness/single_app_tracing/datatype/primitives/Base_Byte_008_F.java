package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.primitives;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型->byte
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// bind_url = completeness/single_app_tracing/datatype/primitives/Base_Byte_008_F/{cmd}
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/primitives")
public class Base_Byte_008_F {
    @PostMapping("Base_Byte_008_F/{cmd}")
    public Map<String, Object> aTaintCase0144_2(@PathVariable Byte cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        Byte b = Byte.valueOf("10");
        try {
            Runtime.getRuntime().exec(String.valueOf(b));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
