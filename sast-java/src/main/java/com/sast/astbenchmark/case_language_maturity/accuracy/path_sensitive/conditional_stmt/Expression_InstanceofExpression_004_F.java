package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.conditional_stmt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 路径敏感-instanceof表达式-null
 * Level 4
 * Date 2024-09-18
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->路径敏感->不涉及求解问题->instanceof表达式-null
// compose = Expression_InstanceofExpression_003_T.java && !Expression_InstanceofExpression_004_F.java
// bind_url = accuracy/pathSensitive/notcalculateValue/Expression_InstanceofExpression_004_F/{cmd}
// assession information end

@RestController()
@RequestMapping("accuracy/pathSensitive/notcalculateValue")
public class Expression_InstanceofExpression_004_F {
    @GetMapping("Expression_InstanceofExpression_004_F/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            if (null instanceof String) {
                Runtime.getRuntime().exec(cmd);
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
