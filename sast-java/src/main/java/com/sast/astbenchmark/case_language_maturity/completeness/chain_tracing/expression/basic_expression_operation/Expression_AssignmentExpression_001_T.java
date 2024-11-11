package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.expression.basic_expression_operation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * Introduction 污点链路样本中的表达式-变量传递通过native方法发生在两个入参上
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->ast对象->变量传递通过native方法发生在两个入参上
// bind_url = completeness/chain_tracing/expression/basic_expression_operation/Expression_AssignmentExpression_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/expression/basic_expression_operation")
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
