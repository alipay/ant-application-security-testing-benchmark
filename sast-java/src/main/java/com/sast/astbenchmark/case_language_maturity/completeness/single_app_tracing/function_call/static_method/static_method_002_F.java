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
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->静态方法
// bind_url = completeness/single_app_tracing/function_call/static_method/static_method_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/static_method")
class MyClass4 {
    private static void __taint_sink(String o) {
        try {
            Runtime.getRuntime().exec(o);
        } catch (Exception e) {
        }
    }

    public static void process(String arg) {
        __taint_sink("safe"); // 传递静态字段到接收点
    }
}

public class static_method_002_F {
    @PostMapping(value = "static_method_002_F")
    public void static_method_002_F(@RequestParam String __taint_src) {
        MyClass4.process(__taint_src);
    }
}

