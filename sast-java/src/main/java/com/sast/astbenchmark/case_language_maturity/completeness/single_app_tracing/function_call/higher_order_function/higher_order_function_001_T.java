package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.higher_order_function;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Introduction 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
 * Level 2
 * Date 2025-02-13
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
// bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/higher_order_function")
public class higher_order_function_001_T {
    public static void applyConsumer(Consumer<String> consumer, String message) {
        consumer.accept(message);
    }

    @PostMapping(value = "higher_order_function_001_T")
    public void higher_order_function_001_T(@RequestParam String __taint_src) {
        applyConsumer(higher_order_function_001_T::__taint_sink, __taint_src);
    }

    private static void __taint_sink(String o) {
        try {
            Runtime.getRuntime().exec(o);
        } catch (IOException e) {
        }
    }

}

