package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.expression.basic_expression_operation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-instanceof表达式
 * Level 2
 * Date 2024-08-16
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->链路跟踪完整度->表达式->基础表达式->instanceof表达式
// bind_url = completeness/chain_tracing/expression/basic_expression_operation/Expression_InstanceofExpression_001_T/{cmd}
// evaluation information end

@RestController()
@RequestMapping("completeness/chain_tracing/expression/basic_expression_operation")
public class Expression_InstanceofExpression_001_T {
    @GetMapping("Expression_InstanceofExpression_001_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable Object cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            if (cmd instanceof String) {
                String data = (String) cmd;
                Runtime.getRuntime().exec(data);
                modelMap.put("status", "success");
            } else {
                modelMap.put("status", "error");
            }
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
