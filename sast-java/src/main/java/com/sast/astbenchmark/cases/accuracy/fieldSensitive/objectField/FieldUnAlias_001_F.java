package com.sast.astbenchmark.cases.accuracy.fieldSensitive.objectField;

import com.sast.astbenchmark.model.alias.B;
import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-FieldUnAlias
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->域敏感->对象属性->对象指针->FieldUnAlias FlowSensitive-scene1
// compose = !FieldUnAlias_001_F.java && FieldUnAlias_002_T.java
// bind_url = accuracy/fieldSensitive/objectField/FieldUnAlias_001_F
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class FieldUnAlias_001_F {
    @PostMapping(value = "FieldUnAlias_001_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        B b1 = new B();
        B b2 = new B();

        b2.attr = b1.attr;
        Invoke.doUnalias(b2);
        b1.attr.b = cmd;

        try {
            Runtime.getRuntime().exec(b2.attr.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
