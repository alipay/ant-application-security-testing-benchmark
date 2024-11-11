package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.function_call.library_function;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-函数调用+函数调用
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->ast对象->函数调用+函数调用
// bind_url = completeness/chain_tracing/function_call/library_function/Expression_MethodInvocation_MethodInvocation_002_T
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/function_call/library_function")
public class Expression_MethodInvocation_MethodInvocation_002_T {
    @PostMapping(value = "Expression_MethodInvocation_MethodInvocation_002_T")
    public Map<String, Object> aTaintCase0114(@RequestParam(defaultValue = "ls") String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd.toUpperCase());
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
