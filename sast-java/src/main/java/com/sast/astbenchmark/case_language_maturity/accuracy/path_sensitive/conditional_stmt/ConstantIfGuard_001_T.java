package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.conditional_stmt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 路径敏感-不涉及求解问题-常量分支条件
 * Level 4
 * Date 2024-05-23
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感->涉及求解问题->数组长度计算
// bind_url = accuracy/path_sensitive/conditional_stmt/DifferentIfBranch_ArrayLength_001_T
// evaluation information end
@RestController
@RequestMapping("accuracy/path_sensitive/conditional_stmt")
public class ConstantIfGuard_001_T {
    @PostMapping(value = "ConstantIfGuard_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String exec = getCmdWithPathSensitive(cmd);
        try {
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    private String getCmdWithPathSensitive(String cmd) {
        int x = 1;
        if (x > 0) {
            return cmd;
        }
        else {
            return "ls";
        }
    }
}
