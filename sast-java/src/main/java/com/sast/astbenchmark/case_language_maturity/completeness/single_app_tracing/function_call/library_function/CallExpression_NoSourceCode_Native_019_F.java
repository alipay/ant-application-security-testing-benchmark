package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.library_function;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->函数和方法调用->库函数调用->string.replace
 * Level 2
 * Date 2024-05-09
 */

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->库函数调用->string.replace
// bind_url = completeness/single_app_tracing/function_call/library_function/CallExpression_NoSourceCode_Native_019_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/library_function")
public class CallExpression_NoSourceCode_Native_019_F {
    @PostMapping(value = "CallExpression_NoSourceCode_Native_019_F")
    public Map<String, Object> aTaintCase0169(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd=cmd.replace(";"," ");
            Runtime.getRuntime().exec("ls"); // 这里构造感觉不是很合适
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
