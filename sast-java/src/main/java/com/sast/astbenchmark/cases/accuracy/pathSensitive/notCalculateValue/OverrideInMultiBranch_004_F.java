package com.sast.astbenchmark.cases.accuracy.pathSensitive.notCalculateValue;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 路径敏感-不涉及求解问题-多分支赋值-scene2
 * Level X
 * Date 2024-06-28
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->路径敏感->不涉及求解问题->多分支赋值-scene2
// compose = OverrideInMultiBranch_003_T.java && !OverrideInMultiBranch_004_F.java
// bind_url = accuracy/pathSensitive/notCalculateValue/OverrideInMultiBranch_004_F
// assession information end
@RestController()
@RequestMapping("accuracy/pathSensitive/notCalculateValue")
public class OverrideInMultiBranch_004_F {
    @PostMapping("OverrideInMultiBranch_004_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] arr = new String[]{"foo", cmd, "bar", "test"};
            if (Math.random() < 0.5) {
                arr[1] = arr[0];
            } else if (Math.random() < 0.5) {
                arr[1] = arr[2];
            } else {
                arr[1] = arr[3];
            }
            Runtime.getRuntime().exec(arr);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}