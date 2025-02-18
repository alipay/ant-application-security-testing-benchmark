package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.argument_return_value_passing.return_value_passing;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction  准确度->上下文敏感分析->参数/返回值传递->返回值传递
 * Level 2
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// bind_url = accuracy/context_sensitive/argument_return_value_passing/return_value_passing/return_value_passing_001_T
// evaluation information end
@RestController()
@RequestMapping("accuracy/context_sensitive/argument_return_value_passing/return_value_passing")
public class return_value_passing_001_T {
    @PostMapping(value = "return_value_passing_001_T")
    public static void returnValuePassing001T(@RequestParam String __taint_src) {

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


