package com.sast.astbenchmark.cases.accuracy.pathSensitive.calculateValue;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 路径敏感-try语句-数组越界异常
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->路径敏感->涉及求解问题->try语句-数组越界异常
// compose = Statement_TryStatement_001_T.java && !Statement_TryStatement_002_F.java
// bind_url = accuracy/pathSensitive/calculateValue/Statement_TryStatement_001_T/{cmd}
// assession information end

@RestController()
@RequestMapping("accuracy/pathSensitive/calculateValue")
public class Statement_TryStatement_001_T {
    @GetMapping("Statement_TryStatement_001_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            int[] data = new int[10];
            if (data[15] > 0)
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
