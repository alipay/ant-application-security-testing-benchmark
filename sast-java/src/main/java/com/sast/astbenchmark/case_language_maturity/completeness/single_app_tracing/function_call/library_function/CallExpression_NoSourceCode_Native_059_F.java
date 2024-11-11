package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.library_function;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的特殊场景-无源码函数-char[],byte[]+Array.copyOfRange
 * Level 2
 * Date 2024-05-09
 */

// evaluation information start
// real case = false
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->特殊场景->无源码函数调用->char[],byte[]+Array.copyOfRange
// bind_url = completeness/single_app_tracing/function_call/library_function/CallExpression_NoSourceCode_Native_059_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/library_function")
public class CallExpression_NoSourceCode_Native_059_F {
    @PostMapping(value = "CallExpression_NoSourceCode_Native_059_F")
    public Map<String, Object> aTaintCase0193(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            byte[] b1 = cmd.getBytes();
            byte[] b2 = Arrays.copyOfRange(b1,0,0);
            Runtime.getRuntime().exec(new String(b2));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
