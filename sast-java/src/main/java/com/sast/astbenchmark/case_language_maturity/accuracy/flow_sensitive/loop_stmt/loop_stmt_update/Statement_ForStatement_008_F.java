package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.loop_stmt.loop_stmt_update;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感分析->循环顺序执行语句->for-update流敏感
// scene introduction = for-update流敏感
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/loop_stmt_update/Statement_ForStatement_008_F/{cmd}
// evaluation information end
@RestController()
@RequestMapping("accuracy/flow_sensitive/loop_stmt/loop_stmt_update")
public class Statement_ForStatement_008_F {
    @GetMapping("Statement_ForStatement_008_F/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a = "mkdir";
            for (int i = 0; i < 1; a = cmd + "|") {
                Runtime.getRuntime().exec(a);
                i++;
            }
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
