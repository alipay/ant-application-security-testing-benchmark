package com.sast.astbenchmark.cases.accuracy.fieldSensitive.objectField;

import com.sast.astbenchmark.model.alias.A;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-HeapOverwriteAlias-scene1
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->域敏感->对象属性->对象指针->HeapOverwriteAlias-scene1
// compose = !HeapOverwriteAlias_002_F.java && HeapOverwriteAlias_001_T.java
// bind_url = accuracy/fieldSensitive/objectField/HeapOverwriteAlias_001_T
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class HeapOverwriteAlias_001_T {
    @PostMapping(value = "HeapOverwriteAlias_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            A a1 = new A();
            A a2 = a1;
            a1.b = cmd;
            a2 = new A();

            Runtime.getRuntime().exec(a1.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
