package com.sast.astbenchmark.cases.accuracy.flowSensitive.InterruptStatement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 中断语句-break+label
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->流敏感->中断语句->break+label
// compose = Statement_InterruptStatement_003_T.java && !Statement_InterruptStatement_004_F.java
// bind_url = accuracy/flowSensitive/InterruptStatement/Statement_InterruptStatement_004_F/{cmd}
// assession information end

@RestController()
@RequestMapping("accuracy/flowSensitive/InterruptStatement")
public class Statement_InterruptStatement_004_F {
    @GetMapping("Statement_InterruptStatement_004_F/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a ="mkdir";
            outerLoop:
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    a = cmd + "|";
                    if (i == 1 && j == 1) {
                        a = "ls";
                        break outerLoop;
                    }
                }
            }
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
