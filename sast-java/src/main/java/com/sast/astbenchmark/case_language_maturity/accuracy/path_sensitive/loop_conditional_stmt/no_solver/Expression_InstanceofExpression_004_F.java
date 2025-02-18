package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.loop_conditional_stmt.no_solver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->路径敏感->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态->instanceof表达式-null
 * Level 3
 * Date 2024-09-18
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/Expression_InstanceofExpression_004_F/{cmd}
// evaluation information end
@RestController()
@RequestMapping("accuracy/path_sensitive/loop_conditional_stmt/no_solver")
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
