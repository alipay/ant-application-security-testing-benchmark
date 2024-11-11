package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.exception_throw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 路径敏感-try语句-数组越界异常
 * Level 4
 * Date 2024-08-16
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感->涉及求解问题->try语句-数组越界异常
// bind_url = accuracy/path_sensitive/exception_throw/Statement_TryStatement_001_T.java
// evaluation information end
@RestController()
@RequestMapping("accuracy/path_sensitive/exception_throw")
public class Statement_TryStatement_001_T {
    @GetMapping("Statement_TryStatement_001_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            int[] data = new int[10];
            int a = data[15];
            cmd = "";
        } catch (Exception ex) {
            try {
                Runtime.getRuntime().exec(cmd);
                modelMap.put("status", "success");
            } catch (Exception e) {
                modelMap.put("status", "error");
            }
        }
        return modelMap;
    }
}
