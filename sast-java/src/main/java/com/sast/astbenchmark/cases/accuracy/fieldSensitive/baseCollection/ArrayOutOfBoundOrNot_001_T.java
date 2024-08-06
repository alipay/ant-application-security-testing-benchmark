package com.sast.astbenchmark.cases.accuracy.fieldSensitive.baseCollection;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-数组是否越界
 * Level X
 * Date 2024-06-28
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->域敏感->容器->数组是否越界
// compose = ArrayOutOfBoundOrNot_001_T.java && !ArrayOutOfBoundOrNot_002_F.java
// bind_url = accuracy/fieldSensitive/baseCollection/ArrayOutOfBoundOrNot_001_T
// assession information end
@RestController()
@RequestMapping("accuracy/fieldSensitive/baseCollection")
public class ArrayOutOfBoundOrNot_001_T {
    @PostMapping("ArrayOutOfBoundOrNot_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] arr = new String[]{"foo", "xx", "bar"};
            arr[2] = cmd;
            Runtime.getRuntime().exec(arr);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}