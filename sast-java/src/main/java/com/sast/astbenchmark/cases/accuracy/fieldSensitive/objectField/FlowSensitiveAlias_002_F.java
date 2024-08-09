package com.sast.astbenchmark.cases.accuracy.fieldSensitive.objectField;

import com.sast.astbenchmark.model.alias.DataClass;
import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-流敏感指针分析-过程间分析-scene1
 * Level X
 * Date 2024-07-02
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->域敏感->对象属性->对象指针->流敏感指针分析-过程间分析-scene1
// compose = FlowSensitiveAlias_001_T.java && !FlowSensitiveAlias_002_F.java
// bind_url = accuracy/fieldSensitive/objectField/FlowSensitiveAlias_002_F
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class FlowSensitiveAlias_002_F {
    @PostMapping(value = "FlowSensitiveAlias_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            DataClass dc1 = new DataClass();
            DataClass dc2 = new DataClass();
            Invoke.copy(dc1, dc2);
            Invoke.taintMe(dc1, cmd);

            Runtime.getRuntime().exec(dc2.data);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
