package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.return_value_passing;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 递归函数-间接递归
 * Level 4 负样本
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->链路跟踪完整度->函数调用->返回值传递->普通
// bind_url = completeness/single_app_tracing/function_call/return_value_passing/return_value_passing_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/return_value_passing")
public class return_value_passing_002_F {
    @PostMapping(value = "return_value_passing_002_F")
    public static void returnValuePassing002F(@RequestParam String __taint_src) {
        String data = process(__taint_src);
        __taint_sink(data);
    }

    private static String process(String __taint_src) {
        String tmp = __taint_src;
        return tmp;
    }

    private static void __taint_sink(Object o) {
    }

}


