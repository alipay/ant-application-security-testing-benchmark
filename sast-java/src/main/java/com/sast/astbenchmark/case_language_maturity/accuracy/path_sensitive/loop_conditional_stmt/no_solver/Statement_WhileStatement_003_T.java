package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.loop_conditional_stmt.no_solver;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->路径敏感->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态->循环语句->while
 * Level 3
 * Date 2024-11-10
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态->循环语句->while
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/Statement_WhileStatement_003_T/{type}/{cmd}
// evaluation information end
@RestController()
@RequestMapping("accuracy/path_sensitive/loop_conditional_stmt/no_solver")
public class Statement_WhileStatement_003_T {
    @GetMapping("Statement_WhileStatement_003_T/{type}/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd, @PathVariable String type) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a ="mkdir";;
            while(StringUtils.equals(type,"mkdir")) {
                a = " "+ cmd;
            }
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
