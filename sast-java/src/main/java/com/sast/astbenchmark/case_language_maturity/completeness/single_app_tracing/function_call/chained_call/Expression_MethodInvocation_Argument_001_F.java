package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.chained_call;

import cn.hutool.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->链式调用
// scene introduction = 函数调用 + BinaryOperation
// level = 2
// bind_url = completeness/single_app_tracing/function_call/chained_call/Expression_MethodInvocation_Argument_001_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/chained_call")
public class Expression_MethodInvocation_Argument_001_F {
    @PostMapping(value = "Expression_MethodInvocation_Argument_001_F")
    public Map<String, Object> aTaintCase0116(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd = "safe";
            HttpRequest.post("http://localhost:39100/ataint/case00124/2?cmd=" + cmd)
                    .execute();
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
