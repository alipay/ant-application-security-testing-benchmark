package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.recursive_function;

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
// evaluation item = 完整度->链路跟踪完整度->函数调用->递归调用->间接调用
// bind_url = completeness/single_app_tracing/function_call/recursive_function/recursive_function_indirect_001_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/recursive_function")
public class recursive_function_indirect_001_F {
    @PostMapping(value = "recursive_function_indirect_001_F")
    public static void recursive_function_direct_001_F(@RequestParam Object __taint_src) {
        Object result = recursiveFunctionDirectA(__taint_src, 3);
        __taint_sink(result);
    }

    private static Object recursiveFunctionDirectA(Object input, int depth) {
        if (depth > 0) {
            return recursiveFunctionDirectB(input, depth - 1);
        } else {
            return input;
        }
    }

    private static Object recursiveFunctionDirectB(Object input, int depth) {
        if (depth > 0) {
            return recursiveFunctionDirectA(input, depth - 1);
        } else {
            return "safe";
        }
    }

    private static void __taint_sink(Object o) {
    }
}

