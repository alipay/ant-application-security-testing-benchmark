package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.function_call.recursive_function;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 递归函数-直接递归
 * Level 4 负样本
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->链路跟踪完整度->函数调用->递归调用->直接调用
// bind_url = completeness/chain_tracing/function_call/recursive_function/recursive_function_direct_002_T
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/function_call/recursive_function")
public class recursive_function_direct_002_T {
    @GetMapping("Expression_Polymorphism_002_F/{cmd}")
    public static void recursive_function_direct_002_T(@RequestParam Object __taint_src) {
        Object result = recursiveFunctionDirect001Func(__taint_src, 3);
        __taint_sink(result);
    }

    private static Object recursiveFunctionDirect001Func(Object input, int depth) {
        if (depth > 0) {
            return recursiveFunctionDirect001Func(input, depth - 1);
        } else {
            return input;
        }
    }

    private static void __taint_sink(Object o) {
    }
}

