package com.sast.astbenchmark.cases.accuracy.contextSensitive;

import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 上下文敏感-相同函数调用不同参数-scene2
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->上下文敏感->相同函数调用不同参数-scene2
// compose = !DifferentParamsForFunction_004_F.java && DifferentParamsForFunction_003_T.java
// bind_url = accuracy/contextSensitive/DifferentParamsForFunction_004_F
// assession information end
@RestController
@RequestMapping("accuracy/contextSensitive")
public class DifferentParamsForFunction_004_F {
    @PostMapping(value = "DifferentParamsForFunction_004_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String c1 = Invoke.id(cmd);
            String c2 = Invoke.id("foo");
            Runtime.getRuntime().exec(c2);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}