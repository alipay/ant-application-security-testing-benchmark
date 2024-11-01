package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.explicit_jump_control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 中断语句-continue+label
 * Level 4
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->流敏感->中断语句->continue+label
// compose = Statement_InterruptStatement_007_T.java && !Statement_InterruptStatement_008_F.java
// bind_url = accuracy/flowSensitive/InterruptStatement/Statement_InterruptStatement_008_F/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Statement_InterruptStatement_008_F {
    @GetMapping("Statement_InterruptStatement_008_F/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a ="mkdir";
            outerLoop:
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    a = cmd + "|";
                    if (i == 9 && j == 9) {
                        a = "ls";
                        continue outerLoop;
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
