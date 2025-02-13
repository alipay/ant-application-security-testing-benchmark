package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.multi_invoke;

import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->上下文敏感分析->多次调用->MultipleCallSite
 * Level 2
 * Date 2024-07-05
 */
// evaluation information start
// real case = true
// evaluation item =  准确度->上下文敏感分析->多次调用->MultipleCallSite
// bind_url = accuracy/context_sensitive/multi_invoke/MultiCallSite_001_T
// evaluation information end
@RestController
@RequestMapping("accuracy/context_sensitive/multi_invoke")
public class MultiCallSite_001_T {
    @PostMapping(value = "MultiCallSite_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd1) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String c1 = Invoke.id8(cmd1);
            String c2 = Invoke.id8(c1);
            Runtime.getRuntime().exec(c2);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
