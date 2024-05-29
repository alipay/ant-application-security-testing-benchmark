package com.sast.astbenchmark.cases.accuracy.pathSensitive.notCalculateValue.multiReturn;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 路径敏感-变量是否被污染
 * Level X
 * Date 2024-05-23
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->路径敏感->不涉及求解问题->多个return语句->变量是否被污染
// compose = !VariableIsTaintOrNot_002_F.java && VariableIsTaintOrNot_001_T.java
// bind_url = accuracy/pathSensitive/notCalculateValue/multiReturn/VariableIsTaintOrNot_001_T
// assession information end
@RestController
@RequestMapping("accuracy/pathSensitive/notCalculateValue/multiReturn")
public class VariableIsTaintOrNot_001_T {
    @PostMapping(value = "VariableIsTaintOrNot_001_T")
    public Map<String, Object> variableIsTaintOrNot_001_T(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String exec = getCmdWithPathSensitive(cmd);
        try {
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    private String getCmdWithPathSensitive(String cmd) {
        int x = 1;
        if (x > 0) {
            return cmd;
        }
        else {
            return "ls";
        }
    }
}
