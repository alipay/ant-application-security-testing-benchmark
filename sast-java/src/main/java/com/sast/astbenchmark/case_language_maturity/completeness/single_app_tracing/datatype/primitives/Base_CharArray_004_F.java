package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.primitives;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型->char[]
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型->char[]
// bind_url = completeness/base/object/javaNative/Base_CharArray_004_F
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_CharArray_004_F {
    @PostMapping("Base_CharArray_004_F")
    public Map<String, Object> aTaintCase0150(@RequestBody char[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        char[] chars = {'a', 'b', 'c', 'd', 'e'};
        try {
            Runtime.getRuntime().exec(String.valueOf(chars));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        modelMap.put("status", "success");
        return modelMap;
    }
}
