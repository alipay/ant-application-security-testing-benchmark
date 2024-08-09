package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-对象是否外部可控
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->对象敏感->同名对象是否真正外部可控
// compose = !ObjectCanBeAssigned_002_F.java && ObjectCanBeAssigned_001_T.java
// bind_url = accuracy/objectSensitive/ObjectCanBeAssigned_002_F
// assession information end
@RestController()
@RequestMapping("accuracy/objectSensitive")
public class ObjectCanBeAssigned_002_F {
    @PostMapping(value = "ObjectCanBeAssigned_002_F")
    public Map<String, Object> testcase(@PathVariable String a) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String exec = "ls";
            String cmd = a ;
            Runtime.getRuntime().exec(exec);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
