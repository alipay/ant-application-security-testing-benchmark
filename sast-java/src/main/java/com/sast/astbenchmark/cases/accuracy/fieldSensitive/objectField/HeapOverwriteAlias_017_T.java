package com.sast.astbenchmark.cases.accuracy.fieldSensitive.objectField;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.B;
import com.sast.astbenchmark.model.alias.C;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-HeapOverwriteAlias FlowSensitive-ThreeAccessPath
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->域敏感->对象属性->对象指针->HeapOverwriteAlias FlowSensitive-ThreeAccessPath
// compose = HeapOverwriteAlias_017_T.java && !HeapOverwriteAlias_018_F.java
// bind_url = accuracy/fieldSensitive/objectField/HeapOverwriteAlias_017_T
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class HeapOverwriteAlias_017_T {
    @PostMapping(value = "HeapOverwriteAlias_017_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            C c = new C();
            c.b = new B();
            c.b.attr = new A();

            A a = c.b.attr;
            a.b = cmd;
            Runtime.getRuntime().exec(c.b.attr.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}