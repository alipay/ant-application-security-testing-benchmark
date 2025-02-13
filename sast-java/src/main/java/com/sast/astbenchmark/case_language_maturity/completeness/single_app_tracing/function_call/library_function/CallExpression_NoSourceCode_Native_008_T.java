package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.library_function;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->函数和方法调用->库函数调用->string.copyValueOf
 * Level 2
 * Date 2024-05-09
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->库函数调用->string.copyValueOf
// bind_url = completeness/single_app_tracing/function_call/library_function/CallExpression_NoSourceCode_Native_008_T
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/library_function")
public class CallExpression_NoSourceCode_Native_008_T {
    @PostMapping(value = "CallExpression_NoSourceCode_Native_008_T")
    public Map<String, Object> aTaintCase0162(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            char data[] =  cmd.toCharArray();
            Runtime.getRuntime().exec(String.copyValueOf(data));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
