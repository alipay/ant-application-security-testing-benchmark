package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.expression.type_cast;

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
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->ast对象->强转表达式-参数传递
// bind_url = completeness/chain_tracing/expression/type_cast/Expression_CastExpression_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/expression/type_cast")
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
