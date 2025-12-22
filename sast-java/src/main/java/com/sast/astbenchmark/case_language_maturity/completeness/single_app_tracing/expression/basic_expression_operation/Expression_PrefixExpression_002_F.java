package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.expression.basic_expression_operation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 前缀表达式
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/Expression_PrefixExpression_002_F
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/expression/basic_expression_operation")
public class Expression_PrefixExpression_002_F {
    @GetMapping("Expression_PrefixExpression_002_F/{cmd}")
    public Map<String, Object> aTaintCase0135(@PathVariable int cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            ++cmd;
            cmd = 0;
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
