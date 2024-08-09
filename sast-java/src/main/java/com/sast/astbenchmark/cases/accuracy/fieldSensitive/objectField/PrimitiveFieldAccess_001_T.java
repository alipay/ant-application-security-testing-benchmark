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
 * Introduction 域敏感-对象属性-对象指针-PrimitiveFieldAccess-scene1
 * Level X
 * Date 2024-07-02
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->域敏感->对象属性->对象指针->PrimitiveFieldAccess-scene1
// compose = PrimitiveFieldAccess_001_T.java && !PrimitiveFieldAccess_002_F.java
// bind_url = accuracy/fieldSensitive/objectField/PrimitiveFieldAccess_001_T
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class PrimitiveFieldAccess_001_T {
    @PostMapping(value = "PrimitiveFieldAccess_001_T")
    public Map<String, Object> testcase(@RequestParam int id) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            id = id + 100;
            B b = new B();
            A a = new A();
            b.attr = a;
            a.i = id;
            Runtime.getRuntime().exec("cat /some/path/" + b.attr.i + ".png");
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}