package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.recursive_function;
import org.springframework.web.bind.annotation.*;

import com.sast.astbenchmark.model.custom.PS;
/**
 * Introduction 递归函数-直接递归
 * Level 4 负样本
 */

// evaluation information start
// real case = false
// evaluation item = 完整度->链路跟踪完整度->函数调用->递归调用->直接调用
// bind_url = completeness/single_app_tracing/function_call/recursive_function/recursive_function_direct_001_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/recursive_function")
public class recursive_function_direct_001_F {

    @PostMapping(value = "recursive_function_direct_001_F")
    public static void recursive_function_direct_001_F(@RequestParam Object __taint_src) {
        Object result = recursiveFunctionDirect001Func(__taint_src, 3);
        __taint_sink(result);
    }

    private static Object recursiveFunctionDirect001Func(Object input, int depth) {
        if (depth > 0) {
            return recursiveFunctionDirect001Func(input, depth - 1);
        } else {
            return "safe";
        }
    }

    private static void __taint_sink(Object o) {
    }
}

