package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.conditional_stmt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->路径敏感->条件语句、条件表达式和循环结构->不涉及求解问题->assert语句-条件不成立
 * Level 4
 * Date 2024-09-18
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感->条件语句、条件表达式和循环结构->不涉及求解问题->assert语句-条件不成立
// bind_url = accuracy/path_sensitive/conditional_stmt/Statement_AssertStatement_003_T/{cmd}
// evaluation information end
@RestController()
@RequestMapping("accuracy/path_sensitive/conditional_stmt")
public class Statement_AssertStatement_003_T {
    @GetMapping("Statement_AssertStatement_003_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            int a = 1;
            assert a > 0 : Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
