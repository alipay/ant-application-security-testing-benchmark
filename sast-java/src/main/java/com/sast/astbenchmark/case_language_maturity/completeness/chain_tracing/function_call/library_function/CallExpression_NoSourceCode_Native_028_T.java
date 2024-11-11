package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.function_call.library_function;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的特殊场景-无源码函数-string substring
 * Level 2
 * Date 2024-05-09
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->特殊场景->无源码函数调用->string.substring
// bind_url = completeness/chain_tracing/function_call/library_function/CallExpression_NoSourceCode_Native_028_T
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/function_call/library_function")
public class CallExpression_NoSourceCode_Native_028_T {
    @PostMapping(value = "CallExpression_NoSourceCode_Native_028_T")
    public Map<String, Object> aTaintCase0174(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd.substring(0,2));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
