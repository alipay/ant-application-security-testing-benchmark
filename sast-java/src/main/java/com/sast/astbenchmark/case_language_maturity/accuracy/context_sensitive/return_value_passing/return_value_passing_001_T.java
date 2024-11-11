package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.return_value_passing;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 递归函数-间接递归
 * Level 4 负样本
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->链路跟踪完整度->函数调用->返回值传递->普通
// bind_url = accuracy/context_sensitive/return_value_passing/return_value_passing_001_T
// evaluation information end
@RestController()
@RequestMapping("accuracy/context_sensitive/return_value_passing")
public class return_value_passing_001_T {
    @PostMapping(value = "return_value_passing_001_T")
    public static void returnValuePassing001T(@RequestParam String __taint_src) {

        String data = process(__taint_src);
        __taint_sink(data);
    }

    private static String process(String __taint_src) {
        String tmp = __taint_src;
        tmp = "_";
        return tmp;
    }

    private static void __taint_sink(Object o) {
    }

}


