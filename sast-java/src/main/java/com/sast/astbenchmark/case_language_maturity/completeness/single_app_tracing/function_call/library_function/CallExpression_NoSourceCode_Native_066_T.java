package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.library_function;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的特殊场景-无源码函数-getBytes+ByteArrayOutputStream
 * Level 2
 * Date 2024-08-16
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->特殊场景->无源码函数调用->getBytes+ByteArrayOutputStream
// bind_url = completeness/single_app_tracing/function_call/library_function/CallExpression_NoSourceCode_Native_066_T
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/library_function")
public class CallExpression_NoSourceCode_Native_066_T {
    @PostMapping(value = "CallExpression_NoSourceCode_Native_066_T")
    public Map<String, Object> testcase(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            byte[] b1 = cmd.getBytes();
            ByteArrayOutputStream b2 = new ByteArrayOutputStream();
            b2.write(b1, 0, b1.length);
            Runtime.getRuntime().exec(b2.toString());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
