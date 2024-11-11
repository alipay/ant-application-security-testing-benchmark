package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.multi_invoke;

import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 上下文敏感-相同函数调用不同参数-scene2
 * Level 2
 * Date 2024-07-05
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感->相同函数调用不同参数-scene2
// bind_url = accuracy/context_sensitive/multi_invoke/DifferentParamsForFunction_003_T
// evaluation information end
@RestController
@RequestMapping("accuracy/context_sensitive/multi_invoke")
public class DifferentParamsForFunction_003_T {
    @PostMapping(value = "DifferentParamsForFunction_003_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String c1 = Invoke.id(cmd);
            String c2 = Invoke.id("foo");
            Runtime.getRuntime().exec(c1);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}