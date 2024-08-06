package com.sast.astbenchmark.cases.accuracy.fieldSensitive.objectField;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.B;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-HeapOverwriteAlias-TwoAccessPath
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->域敏感->对象属性->对象指针->HeapOverwriteAlias ContextSensitive-TwoAccessPath
// compose = HeapOverwriteAlias_015_T.java && !HeapOverwriteAlias_016_F.java
// bind_url = accuracy/fieldSensitive/objectField/HeapOverwriteAlias_016_F
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class HeapOverwriteAlias_016_F {
    @PostMapping(value = "HeapOverwriteAlias_016_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            A a = new A();
            B b = new B();
            b.attr = a;
            a.b = cmd;
            b.setAttr(new A());
            Runtime.getRuntime().exec(b.attr.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}