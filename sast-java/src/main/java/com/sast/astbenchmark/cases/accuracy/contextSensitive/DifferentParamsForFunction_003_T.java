package com.sast.astbenchmark.cases.accuracy.contextSensitive;

import com.sast.astbenchmark.common.utils.CmdUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 上下文敏感-调用相同函数传入不同参数
 * Level X
 * Date 2024-05-23
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->上下文敏感->调用相同函数传入不同参数
// compose = !DifferentParamsForFunction_004_F.java && DifferentParamsForFunction_003_T.java
// bind_url = accuracy/contextSensitive/DifferentParamsForFunction_003_T
// assession information end
@RestController
@RequestMapping("accuracy/contextSensitive")
public class DifferentParamsForFunction_003_T {
    @PostMapping(value = "DifferentParamsForFunction_003_T")
    public Map<String, Object> aTaintCase002(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String exec = CmdUtil.getCmdWithContextSensitive(1, cmd);
        try {
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
