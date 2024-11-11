package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.function_call.static_method;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 高阶函数-匿名函数为参数
 * Level 3
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->链路完整度->函数调用->静态->静态方法
// bind_url = completeness/chain_tracing/function_call/static_method/static_method_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/function_call/static_method")
class MyClass4 {
    private static void __taint_sink(Object o) {

    }
    public static void process(Object arg) {
        __taint_sink("safe"); // 传递静态字段到接收点
    }
}

public class static_method_002_F {
    @PostMapping(value = "static_method_002_F")
    public void static_method_002_F(@RequestParam Object __taint_src) {
        MyClass4.process(__taint_src);
    }
}

