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
 * Introduction 域敏感-对象属性-对象指针-过程间别名分析
 * Level X
 * Date 2024-07-02
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->域敏感->对象属性->对象指针->过程间别名分析
// compose = InterproceduralAlias_001_T.java && !InterproceduralAlias_002_F.java
// bind_url = accuracy/fieldSensitive/objectField/InterproceduralAlias_001_T
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class InterproceduralAlias_001_T {
    @PostMapping(value = "InterproceduralAlias_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            DataClass dc1 = new DataClass();
            Invoke.taintMe(dc1, cmd);

            Runtime.getRuntime().exec(dc1.data);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
