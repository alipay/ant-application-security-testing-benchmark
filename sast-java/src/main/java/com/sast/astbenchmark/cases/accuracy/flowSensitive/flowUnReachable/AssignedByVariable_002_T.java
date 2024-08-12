package com.sast.astbenchmark.cases.accuracy.flowSensitive.flowUnReachable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 数据流不可达->被固定值通过变量间接赋值
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->流敏感->数据流不可达->被固定值通过变量间接赋值
// compose = !AssignedByVariable_001_F.java && AssignedByVariable_002_T.java
// bind_url = accuracy/flowSensitive/flowUnReachable/AssignedByVariable_002_T
// assession information end
@RestController()
@RequestMapping("accuracy/flowSensitive/flowUnReachable")
public class AssignedByVariable_002_T {
    @PostMapping(value = "AssignedByVariable_002_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a = cmd;
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
