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
// real vulnerability = true
// assession project = 准确度->对象敏感->同名对象是否真正外部可控
// compose = !ObjectCanBeAssigned_002_F.java && ObjectCanBeAssigned_001_T.java
// bind_url = completeness/base/chain/astTaint/ObjectCanBeAssigned_001_T
// assession information end
@RestController()
@RequestMapping("accuracy/objectSensitive/objectCanBeAssigned")
public class ObjectCanBeAssigned_001_T {
    @PostMapping(value = "ObjectCanBeAssigned_001_T")
    public Map<String, Object> aTaintCase021(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String exec = "ls";
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
