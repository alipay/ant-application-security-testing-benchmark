package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.return_value_passing;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
// scene introduction = 普通
// level = 2
// bind_url = completeness/single_app_tracing/function_call/return_value_passing/function_return_value_passing_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/return_value_passing")
public class function_return_value_passing_001_T {
    @PostMapping(value = "function_return_value_passing_001_T")
    public static void returnValuePassing001T(@RequestParam String __taint_src) {
        String data = process(__taint_src);
        __taint_sink(data);
    }

    private static String process(String __taint_src) {
        String tmp = __taint_src;
        return tmp;
    }

    private static void __taint_sink(String o) {
        try {
            Runtime.getRuntime().exec(o);
        } catch (Exception e) {
        }
    }

}
