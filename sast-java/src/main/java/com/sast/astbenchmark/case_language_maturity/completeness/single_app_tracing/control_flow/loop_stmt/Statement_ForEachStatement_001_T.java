package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.control_flow.loop_stmt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->流程控制语句->循环结构->foreach语句
 * Level 2
 * Date 2024-11-10
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/Statement_ForEachStatement_001_T/{cmd}
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/control_flow/loop_stmt")
public class Statement_ForEachStatement_001_T {
    @GetMapping("Statement_ForEachStatement_001_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] data = new String[] {"ls", "foo", cmd};
            String a = "mkdir";
            for (String i : data) {
                a = i + "|";
            }
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
