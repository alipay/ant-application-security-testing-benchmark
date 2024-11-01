package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.references_pointers.alias;

import com.sast.astbenchmark.model.alias.B;
import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度-链路跟踪完整度-引用和指针-别名-对象属性-对象指针-FieldUnAlias
 * Level 3
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = true
// assession project =  完整度->链路跟踪完整度->引用和指针->别名->对象属性->对象指针->FieldUnAlias FlowSensitive-scene1
// compose = !FieldUnAlias_001_F.java && FieldUnAlias_002_T.java
// bind_url = accuracy/fieldSensitive/objectField/FieldUnAlias_002_T
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class FieldUnAlias_002_T {
    @PostMapping(value = "FieldUnAlias_002_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        B b1 = new B();
        B b2 = new B();

        b2.attr = b1.attr;
        b1.attr.b = cmd;
        Invoke.doUnalias(b1);

        try {
            Runtime.getRuntime().exec(b2.attr.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
