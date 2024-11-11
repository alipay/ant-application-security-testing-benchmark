package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.expression.basic_expression_operation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-后缀表达式
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->链路跟踪完整度->表达式->基础表达式->后缀表达式
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/Expression_PostfixExpression_001_T/{cmd}
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/expression/basic_expression_operation")
public class Expression_PostfixExpression_001_T {

    @GetMapping("Expression_PostfixExpression_001_T/{cmd}")
    public Map<String, Object> aTaintCase0136(@PathVariable int cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd++;
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
