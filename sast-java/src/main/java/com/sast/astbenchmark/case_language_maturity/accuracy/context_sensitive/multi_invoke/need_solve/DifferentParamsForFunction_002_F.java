package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.multi_invoke.need_solve;

import com.sast.astbenchmark.common.utils.CmdUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->上下文敏感分析->多次调用->可求解
 * Level 4
 * Date 2024-07-05
 */
// evaluation information start
// real case = false
// evaluation item =  准确度->上下文敏感分析->多次调用->可求解
// bind_url = accuracy/context_sensitive/multi_invoke/need_solve/DifferentParamsForFunction_002_F
// evaluation information end
@RestController
@RequestMapping("accuracy/context_sensitive/multi_invoke/need_solve")
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
