package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.expression.basic_expression_operation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-位运算
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->链路跟踪完整度->表达式->基础表达式->位运算操作
// bind_url = completeness/chain_tracing/expression/basic_expression_operation/Expression_BitOperation_002_F
// evaluation information end

@RestController()
@RequestMapping("completeness/chain_tracing/expression/basic_expression_operation")
public class Expression_BitOperation_002_F {
    @PostMapping(value = "Expression_BitOperation_002_F")
    public Map<String, Object> aTaintCase0159(@RequestParam char cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd= (char) (cmd<<1);
            cmd = 'c';
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
