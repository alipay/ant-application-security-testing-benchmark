package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.expression.basic_expression_operation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-中缀表达式+对象创建
 * Level 2
 * Date 2024-05-09
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->ast对象->中缀表达式+对象创建
// bind_url = completeness/chain_tracing/expression/basic_expression_operation/Expression_ClassInstance_Infix_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/expression/basic_expression_operation")
public class Expression_ClassInstance_Infix_001_T {
    @PostMapping(value = "Expression_ClassInstance_Infix_001_T")
    public Map<String, Object> aTaintCase0113(@RequestParam(defaultValue = "ls") String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(new String(cmd+" &"));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
