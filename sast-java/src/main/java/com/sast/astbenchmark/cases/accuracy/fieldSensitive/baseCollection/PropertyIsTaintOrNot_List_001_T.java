package com.sast.astbenchmark.cases.accuracy.fieldSensitive;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Introduction 域敏感->对象部分属性为污点
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->域敏感->对象部分属性为污点->List
// compose = !PropertyIsTaintOrNot_List_002_F.java && PropertyIsTaintOrNot_List_001_T.java
// bind_url = accuracy/fieldSensitive/PropertyIsTaintOrNot_List_001_T
// assession information end

@RestController()
@RequestMapping("accuracy/fieldSensitive")
public class PropertyIsTaintOrNot_List_001_T {
    @PostMapping(value = "PropertyIsTaintOrNot_List_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            LinkedList<String> data = new LinkedList<String>();
            data.add("hello world");
            data.add(cmd);
            data.add("not tainted");
            Runtime.getRuntime().exec(data.get(1));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
