package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.loop_stmt.loop_stmt_init;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->循环顺序执行语句->for-init流敏感
// scene introduction = for-init流敏感
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/loop_stmt_init/Statement_ForStatement_005_T/{cmd}
// evaluation information end
@RestController()
@RequestMapping("accuracy/flow_sensitive/loop_stmt/loop_stmt_init")
public class Statement_ForStatement_005_T {
    @GetMapping("Statement_ForStatement_005_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a = "mkdir";
            int i = 0;
            for (a = cmd + "|"; i < 1; i++) {
                Runtime.getRuntime().exec(a);
            }
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
