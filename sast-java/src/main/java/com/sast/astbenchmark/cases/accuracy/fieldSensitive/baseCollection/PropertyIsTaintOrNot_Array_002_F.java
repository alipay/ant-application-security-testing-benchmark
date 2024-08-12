package com.sast.astbenchmark.cases.accuracy.fieldSensitive;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感->对象部分属性为污点
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->域敏感->对象部分属性为污点->Array
// compose = !PropertyIsTaintOrNot_Array_002_F.java && PropertyIsTaintOrNot_Array_001_T.java
// bind_url = accuracy/fieldSensitive/PropertyIsTaintOrNot_Array_002_F
// assession information end

@RestController()
@RequestMapping("accuracy/fieldSensitive")
public class PropertyIsTaintOrNot_Array_002_F {
    @PostMapping(value = "PropertyIsTaintOrNot_Array_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] strings = new String[3];
            strings[0]="cd ~";
            strings[1]=cmd;
            strings[2]="cd /";
            Runtime.getRuntime().exec(strings[0]);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
