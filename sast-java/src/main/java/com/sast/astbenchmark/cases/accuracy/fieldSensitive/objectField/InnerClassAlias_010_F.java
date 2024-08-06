package com.sast.astbenchmark.cases.accuracy.fieldSensitive.objectField;

import com.sast.astbenchmark.model.alias.Inner1b;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-InnerClassAlias-scene4
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->域敏感->对象属性->对象指针->InnerClassAlias-scene4
// compose = InnerClassAlias_009_T.java && !InnerClassAlias_010_F.java
// bind_url = accuracy/fieldSensitive/objectField/InnerClassAlias_010_F
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class InnerClassAlias_010_F {
    @PostMapping(value = "InnerClassAlias_010_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Inner1b a = new Inner1b();
            a.obj = a.new Inner2b();

            Inner1b b = new Inner1b();
            b.parentData = "foo";

            a.obj = b.new Inner2b();
            a.parentData = cmd;
            Inner1b.Inner2b inner = a.obj;

            Runtime.getRuntime().exec(inner.getParent()); // null
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
