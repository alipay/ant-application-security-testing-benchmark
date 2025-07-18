package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.argument_passing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 变量赋值表达式直接引入而不是import
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/Expression_CallExpression_Package_001_T/{url}
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/argument_passing")
public class Expression_CallExpression_Package_001_T {
    @GetMapping("Expression_NewExpression_Package_001_T/{url}")
    public Map<String, Object> aTaintCase0157(@PathVariable String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String result = com.sast.astbenchmark.common.utils.HttpUtil.doGet(url);
            modelMap.put("status", "success");
            modelMap.put("result", result);
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
