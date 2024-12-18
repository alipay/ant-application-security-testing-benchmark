package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.expression.basic_expression_operation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-变量定义语句
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->链路跟踪完整度->表达式->基础表达式->变量定义语句
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/Statement_VariableDeclarationStatement_001_T
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/expression/basic_expression_operation")
public class Statement_VariableDeclarationStatement_001_T {
    @PostMapping("Statement_VariableDeclarationStatement_001_T")
    public Map<String, Object> aTaintCase019(@RequestParam char[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        char[] data = cmd;
        try {
            Runtime.getRuntime().exec(new String(data));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}