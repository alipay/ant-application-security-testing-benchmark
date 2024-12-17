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
// real case = false
// evaluation item = 准确度->上下文敏感->返回值传递
// bind_url = accuracy/context_sensitive/return_value_passing/return_value_passing_002_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/context_sensitive/return_value_passing")
public class return_value_passing_002_F {
    @PostMapping(value = "return_value_passing_002_F")
    public static void returnValuePassing002F(@RequestParam String __taint_src) {

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


