package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.expression.basic_expression_operation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * Introduction 完整度->单应用跟踪完整度->表达式->基础表达式->变量传递通过native方法发生在两个入参上
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/Expression_AssignmentExpression_001_T/{cmd}
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/expression/basic_expression_operation")
public class Expression_AssignmentExpression_001_T {
    @PostMapping("Expression_AssignmentExpression_001_T/{cmd}")
    public Map<String, Object> aTaintCase018(@PathVariable String[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            String[] b = {"a","b"};
            System.arraycopy(cmd,0,b,0,2);
            Runtime.getRuntime().exec(b[0]);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
