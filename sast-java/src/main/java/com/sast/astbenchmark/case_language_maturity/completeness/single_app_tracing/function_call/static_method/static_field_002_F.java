package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.static_method;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Introduction 完整度->单应用跟踪完整度->函数和方法调用->静态方法->静态字段
 * Level 2
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->静态方法->静态字段
// bind_url = completeness/single_app_tracing/function_call/static_method/static_field_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/static_method")
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

