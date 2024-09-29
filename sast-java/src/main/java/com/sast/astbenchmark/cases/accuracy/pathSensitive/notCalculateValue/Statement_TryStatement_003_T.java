package com.sast.astbenchmark.cases.accuracy.pathSensitive.notCalculateValue;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 路径敏感-try语句-抛出异常
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->路径敏感->不涉及求解问题->try语句-抛出异常
// compose = Statement_TryStatement_003_T.java && !Statement_TryStatement_004_F.java
// bind_url = accuracy/pathSensitive/notcalculateValue/Statement_TryStatement_003_T/{cmd}
// assession information end

@RestController()
@RequestMapping("accuracy/pathSensitive/notcalculateValue")
public class Statement_TryStatement_003_T {
    @GetMapping("Statement_TryStatement_003_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            throw new RuntimeException(cmd);
        } catch (RuntimeException ex) {
            try {
                Runtime.getRuntime().exec(ex.getMessage());
                modelMap.put("status", "success");
            } catch (Exception e) {
            modelMap.put("status", "error");
            }
        }
        return modelMap;
    }
}
