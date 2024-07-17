package com.sast.astbenchmark.cases.accuracy.contextSensitive;

import com.sast.astbenchmark.common.utils.CmdUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 上下文敏感-相同函数调用不同参数
 * Level X
 * Date 2024-05-23
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->上下文敏感->相同函数调用不同参数
// compose = !DifferentParamsForFunction_002_F.java && DifferentParamsForFunction_001_T.java
// bind_url = accuracy/contextSensitive/DifferentParamsForFunction_002_F
// assession information end
public class DifferentParamsForFunction_002_F {
    @PostMapping(value = "DifferentParamsForFunction_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String exec = CmdUtil.getCmdWithContextSensitive(-1, cmd);
        try {
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
