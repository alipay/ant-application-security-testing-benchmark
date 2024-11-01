package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.function_call.library_function;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Introduction 污点链路样本中的特殊场景-无源码函数-char[],byte[]+Array.copyOf
 * Level 2
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->无源码函数调用->char[],byte[]+Array.copyOf
// compose = CallExpression_NoSourceCode_Native_029_T.java
// bind_url = completeness/base/chain/special/nosource/CallExpression_NoSourceCode_Native_029_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/special/nosource")
public class CallExpression_NoSourceCode_Native_029_T {
    @PostMapping(value = "CallExpression_NoSourceCode_Native_029_T")
    public Map<String, Object> aTaintCase0192(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            byte[] b1 = cmd.getBytes();
            byte[] b2 = Arrays.copyOf(b1,10);
            Runtime.getRuntime().exec(new String(b2));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
