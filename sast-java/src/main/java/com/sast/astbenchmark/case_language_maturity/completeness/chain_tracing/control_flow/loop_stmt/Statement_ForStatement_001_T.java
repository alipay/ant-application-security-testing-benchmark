package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.control_flow.loop_stmt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的语句-for语句
 * Level 2
 * Date 2024-05-09
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->ast对象->for语句
// bind_url = completeness/chain_tracing/control_flow/loop_stmt/Statement_ForStatement_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/control_flow/loop_stmt")
public class Statement_ForStatement_001_T {
    @GetMapping("Statement_ForStatement_001_T/{cmd}")
    public Map<String, Object> aTaintCase0127(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a ="mkdir";
            for(int i =0 ;i<10; i++){
                a= cmd+"|";
            }
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
