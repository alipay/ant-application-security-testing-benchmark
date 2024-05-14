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
// real vulnerability = true
// assession project = 准确度->流敏感->数据流不可达->被固定值赋值
// compose = !AssignedByFixedValue_001_F.java && AssignedByFixedValue_002_T.java
// bind_url = completeness/base/chain/astTaint/AssignedByFixedValue_002_T
// assession information end
@RestController()
@RequestMapping("accuracy/flowSensitive/flowUnReachable")
public class AssignedByFixedValue_002_T {
    @PostMapping(value = "AssignedByFixedValue_002_T")
    public Map<String, Object> caseTest(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
