package com.sast.astbenchmark.cases.accuracy.pathSensitive.notCalculateValue;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Introduction 路径敏感-不涉及求解问题-多分支赋值-scene1
 * Level X
 * Date 2024-06-28
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->路径敏感->不涉及求解问题->多分支赋值-scene1
// compose = OverrideInMultiBranch_001_T.java && !OverrideInMultiBranch_002_F.java
// bind_url = accuracy/pathSensitive/notCalculateValue/OverrideInMultiBranch_001_T
// assession information end
@RestController()
@RequestMapping("accuracy/pathSensitive/notCalculateValue")
public class OverrideInMultiBranch_001_T {
    @PostMapping("OverrideInMultiBranch_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String cmd1 = cmd;
            if (new Random().nextBoolean()) {
                cmd1 = "ls";
            } else if (new Random().nextBoolean()) {
                cmd1 = "ll";
            }
            Runtime.getRuntime().exec(cmd1);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
