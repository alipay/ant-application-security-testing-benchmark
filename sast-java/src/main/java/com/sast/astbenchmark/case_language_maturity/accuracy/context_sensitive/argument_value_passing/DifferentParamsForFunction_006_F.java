package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.argument_value_passing;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 上下文敏感-相同函数调用不同参数-VarargArrayAccess
 * Level 3
 * Date 2024-06-28
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感->相同函数调用不同参数->VarargArrayAccess
// bind_url = accuracy/context_sensitive/argument_value_passing/DifferentParamsForFunction_006_F
// evaluation information end

@RestController()
@RequestMapping("accuracy/context_sensitive/argument_value_passing")
public class DifferentParamsForFunction_006_F {
    @PostMapping("DifferentParamsForFunction_006_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {

        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a = Invoke.chooseOne(2, "a", "b", "c", cmd, "e");
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    private static class Invoke {
        private static String chooseOne(int i, String... params) {
            return params[i];
        }
    }
}