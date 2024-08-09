package com.sast.astbenchmark.cases.accuracy.fieldSensitive.objectField;

import com.sast.astbenchmark.model.alias.Inner3;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-InnerClassAlias-scene3
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->域敏感->对象属性->对象指针->InnerClassAlias-scene3
// compose = !InnerClassAlias_008_F.java && InnerClassAlias_007_T.java
// bind_url = accuracy/fieldSensitive/objectField/InnerClassAlias_008_F
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class InnerClassAlias_008_F {
    @PostMapping(value = "InnerClassAlias_008_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Inner3 a = new Inner3();
            Inner3 b = new Inner3();

            a.obj2 = b.new Inner2b();
            a.obj2.foo = b.new Inner2();

            a.obj2.foo.set(cmd);
            String s = a.get(); // untainted

            Runtime.getRuntime().exec(s);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
