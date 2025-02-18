package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.loop_conditional_stmt.no_solver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->路径敏感->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态->循环语句->for
 * Level 3
 * Date 2024-11-10
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态->循环语句->for
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/Statement_ForStatement_004_F.java/{cmd}
// evaluation information end
@RestController()
@RequestMapping("accuracy/path_sensitive/loop_conditional_stmt/no_solver")
public class Statement_ForStatement_004_F {
    @GetMapping("Statement_ForStatement_004_F.java/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a ="mkdir";
            for(int i =0 ;i<0; i++){
                a = cmd+"|";
            }
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
