package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.primitives;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型->char
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型->char
// bind_url = completeness/base/object/javaNative/Base_Char_006_F/{cmd}
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_Char_006_F {
    @GetMapping("Base_Char_006_F/{cmd}")
    public Map<String, Object> aTaintCase0137(@PathVariable char cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        char c = '_';
        try {
            Runtime.getRuntime().exec(String.valueOf(c));
            modelMap.put("status", "success");
        }
        catch (Exception e){
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
