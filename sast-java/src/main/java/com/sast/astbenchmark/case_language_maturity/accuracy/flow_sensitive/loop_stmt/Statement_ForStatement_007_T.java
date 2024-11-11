package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.loop_stmt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 循环语句-for-update流敏感
 * Level 3
 * Date 2024-11-10
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感->循环语句->for-update流敏感
// bind_url = accuracy/flow_sensitive/loop_stmt/Statement_ForStatement_007_T.java/{cmd}
// evaluation information end
@RestController()
@RequestMapping("accuracy/flow_sensitive/loop_stmt")
public class Statement_ForStatement_007_T {
    @GetMapping("Statement_ForStatement_007_T.java/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a ="mkdir";
            for(int i = 0; i<1; Runtime.getRuntime().exec(a)){
                a= cmd+"|";
                i++;
            }
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
