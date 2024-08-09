package com.sast.astbenchmark.cases.accuracy.fieldSensitive.objectField;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.B;
import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-FieldUnAlias
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->域敏感->对象属性->对象指针->FieldUnAlias ContextSensitive-scene2
// compose = !FieldUnAlias_004_F.java && FieldUnAlias_003_T.java
// bind_url = accuracy/fieldSensitive/objectField/FieldUnAlias_003_T
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class FieldUnAlias_003_T {
    @PostMapping(value = "FieldUnAlias_003_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        B b1 = new B();
        b1.attr = new A();
        b1.attr.b = cmd;
        B b2 = new B();
        Invoke.alias(b1, b2);
        Invoke.doUnalias(b2);
        b2.attr.b = "bar";
        try {
            Runtime.getRuntime().exec(b1.attr.b);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}