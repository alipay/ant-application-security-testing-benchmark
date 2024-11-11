package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.expression.type_cast;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的语句-强转表达式
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->链路跟踪完整度->表达式->强转表达式-参数传递
// bind_url = completeness/single_app_tracing/expression/type_cast/Expression_CastExpression_001_T/{cmd}
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/expression/type_cast")
public class Expression_CastExpression_001_T {
    @GetMapping("Expression_CastExpression_001_T/{cmd}")
    public Map<String, Object> aTaintCase0129(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Object cmdObject = new Object();
            cmdObject=cmd;
            Runtime.getRuntime().exec((String) cmdObject);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
