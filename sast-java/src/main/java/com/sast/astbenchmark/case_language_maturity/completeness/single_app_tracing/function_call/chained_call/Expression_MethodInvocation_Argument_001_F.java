package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.chained_call;

import cn.hutool.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-函数调用+参数获取
 * Level 3
 * Date 2024-05-09
 */

// evaluation information start
// real case = false
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->ast对象->函数调用 + BinaryOperation
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
