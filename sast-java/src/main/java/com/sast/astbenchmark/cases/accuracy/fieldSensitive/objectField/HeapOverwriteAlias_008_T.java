package com.sast.astbenchmark.cases.accuracy.fieldSensitive.objectField;

import com.sast.astbenchmark.model.alias.J;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-HeapOverwriteAlias FlowSensitive-scene4
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->域敏感->对象属性->对象指针->HeapOverwriteAlias FlowSensitive-scene4
// compose = HeapOverwriteAlias_007_T.java && HeapOverwriteAlias_008_T.java && !HeapOverwriteAlias_009_F.java
// bind_url = accuracy/fieldSensitive/objectField/HeapOverwriteAlias_008_T
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class HeapOverwriteAlias_008_T {
    @PostMapping(value = "HeapOverwriteAlias_008_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            J j = new J();
            j.read();
            Runtime.getRuntime().exec(j.e.str);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
