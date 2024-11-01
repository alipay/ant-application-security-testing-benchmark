package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.multi_invoke;

import com.sast.astbenchmark.common.utils.CmdUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 上下文敏感-相同函数调用不同参数-scene1
 * Level 4
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->上下文敏感->相同函数调用不同参数-scene1
// compose = !DifferentParamsForFunction_002_F.java && DifferentParamsForFunction_001_T.java
// bind_url = accuracy/contextSensitive/DifferentParamsForFunction_002_F
// assession information end
@RestController
@RequestMapping("accuracy/contextSensitive")
public class DifferentParamsForFunction_002_F {
    @PostMapping(value = "DifferentParamsForFunction_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String exec1, exec2;
        int i = 1;
        exec1 = CmdUtil.getCmdWithContextSensitive(i, cmd);
        i = -1;
        exec2 = CmdUtil.getCmdWithContextSensitive(i, cmd);
        try {
            Runtime.getRuntime().exec(exec2);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
