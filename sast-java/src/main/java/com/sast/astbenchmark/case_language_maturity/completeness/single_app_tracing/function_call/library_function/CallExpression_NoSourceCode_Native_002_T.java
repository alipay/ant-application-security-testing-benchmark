package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.library_function;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->库函数调用
// scene introduction = string.valueof
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/library_function/CallExpression_NoSourceCode_Native_002_T
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/library_function")
public class CallExpression_NoSourceCode_Native_002_T {
    @PostMapping(value = "CallExpression_NoSourceCode_Native_002_T")
    public Map<String, Object> aTaintCase0155(@RequestParam(defaultValue = "ls") String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String exec = String.valueOf(cmd);
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
