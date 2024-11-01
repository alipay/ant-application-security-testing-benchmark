package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.normal_stmt;

import com.sast.astbenchmark.model.alias.DataClass;
import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 流敏感-对象属性-对象指针-流敏感指针分析-过程间分析-scene1
 * Level 3
 * Date 2024-07-02
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->流敏感->对象属性->对象指针->流敏感指针分析-过程间分析-scene1
// compose = FlowSensitiveAlias_001_T.java && !FlowSensitiveAlias_002_F.java
// bind_url = accuracy/fieldSensitive/objectField/FlowSensitiveAlias_001_T
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class FlowSensitiveAlias_001_T {
    @PostMapping(value = "FlowSensitiveAlias_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            DataClass dc1 = new DataClass();
            DataClass dc2 = new DataClass();
            Invoke.taintMe(dc1, cmd);
            Invoke.copy(dc1, dc2);

            Runtime.getRuntime().exec(dc2.data);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
