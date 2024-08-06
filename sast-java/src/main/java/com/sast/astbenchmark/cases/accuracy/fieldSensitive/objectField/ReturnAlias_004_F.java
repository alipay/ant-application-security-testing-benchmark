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
 * Introduction 域敏感-对象属性-对象指针-ReturnAlias FlowSensitive-scene2
 * Level X
 * Date 2024-07-02
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->域敏感->对象属性->对象指针->ReturnAlias FlowSensitive-scene2
// compose = ReturnAlias_003_T.java && !ReturnAlias_004_F.java
// bind_url = accuracy/fieldSensitive/objectField/ReturnAlias_004_F
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class ReturnAlias_004_F {
    @PostMapping(value = "ReturnAlias_004_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            A a = new A();
            A b = Invoke.alias(a);
            A c = Invoke.alias(b);
            c.b = cmd;
            A d = Invoke.alias(c);
            A e = Invoke.alias(d);
            a.b = "foo";

            Runtime.getRuntime().exec(e.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
