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
// real case = true
// evaluation item = 完整度->链路完整度->函数调用->静态->静态字段
// bind_url = completeness/chain_tracing/function_call/static_method/static_field_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/function_call/static_method")
class MyClass {
    static Object data;
}

public class static_field_001_T {
    @PostMapping(value = "static_field_001_T")
    public void static_field_001_T(@RequestParam Object __taint_src) {
        MyClass.data = __taint_src;
        process();
    }

    private void process(){
        __taint_sink(MyClass.data);
    }

    private void __taint_sink(Object o) {

    }

}

