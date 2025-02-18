package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.static_method;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 完整度->单应用跟踪完整度->函数和方法调用->静态方法->静态方法
 * Level 2
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->静态方法
// bind_url = completeness/single_app_tracing/function_call/static_method/static_method_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/static_method")
class MyClass3 {
    private static void __taint_sink(Object o) {

    }
    public static void process(Object arg) {
        __taint_sink(arg); // 传递静态字段到接收点
    }
}

public class static_method_001_T {
    @PostMapping(value = "static_method_001_T")
    public void static_method_001_T(@RequestParam Object __taint_src) {
        MyClass3.process(__taint_src);
    }
}

