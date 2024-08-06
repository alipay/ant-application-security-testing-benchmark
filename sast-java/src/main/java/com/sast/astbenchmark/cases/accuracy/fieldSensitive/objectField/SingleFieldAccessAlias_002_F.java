package com.sast.astbenchmark.cases.accuracy.fieldSensitive.objectField;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-SingleFieldAccessAlias
 * Level X
 * Date 2024-07-02
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->域敏感->对象属性->对象指针->SingleFieldAccessAlias
// compose = SingleFieldAccessAlias_001_T.java && !SingleFieldAccessAlias_002_F.java
// bind_url = accuracy/fieldSensitive/objectField/SingleFieldAccessAlias_002_F
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class SingleFieldAccessAlias_002_F {
    @PostMapping(value = "SingleFieldAccessAlias_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            A a = new A();
            a.b = cmd;
            A b = Invoke.alias(a);
            b.b = "cmd";

            Runtime.getRuntime().exec(b.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
