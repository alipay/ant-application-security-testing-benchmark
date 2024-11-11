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
// evaluation item = 完整度->链路完整度->函数调用->静态->静态字段
// bind_url = completeness/chain_tracing/function_call/static_method/static_field_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/function_call/static_method")
class MyClass2 {
    static Object data;
}

public class static_field_002_F {
    @PostMapping(value = "static_field_002_F")
    public void static_field_002_F(@RequestParam Object __taint_src) {
        MyClass2.data = "safe";
        process();
    }

    private void process(){
        __taint_sink(MyClass2.data);
    }

    private void __taint_sink(Object o) {

    }

}

