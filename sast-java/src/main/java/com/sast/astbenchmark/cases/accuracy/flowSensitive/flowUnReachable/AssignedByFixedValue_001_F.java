package com.sast.astbenchmark.cases.accuracy.flowSensitive.flowUnReachable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 数据流不可达->被固定值赋值
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->流敏感->数据流不可达->被固定值赋值
// compose = !AssignedByFixedValue_001_F.java && AssignedByFixedValue_002_T.java
// bind_url = accuracy/flowSensitive/flowUnReachable/AssignedByFixedValue_001_F
// assession information end
@RestController()
@RequestMapping("accuracy/flowSensitive/flowUnReachable")
public class AssignedByFixedValue_001_F {
    @PostMapping(value = "AssignedByFixedValue_001_F")
    public Map<String, Object> aTaintCase0158(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd= "ls";
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
