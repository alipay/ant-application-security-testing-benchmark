package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.control_flow.loop_stmt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->循环结构
// scene introduction = do_while语句
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/loop_stmt/Statement_DoStatement_001_T/{cmd}
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/control_flow/loop_stmt")
public class Statement_DoStatement_001_T {
    @GetMapping("Statement_DoStatement_001_T/{cmd}")
    public Map<String, Object> aTaintCase0128(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a = "mkdir";
            int i = 10;

            do {
                a = cmd + "|";
                i++;
            } while (i < 20);

            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
