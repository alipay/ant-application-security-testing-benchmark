package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.references_pointers.alias;

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
 * Introduction 完整度-链路跟踪完整度-引用和指针-别名-对象属性-对象指针-FieldUnAlias
 * Level 3
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->链路跟踪完整度->引用和指针->别名->对象属性->对象指针->FieldUnAlias ContextSensitive-scene2
// compose = !FieldUnAlias_004_F.java && FieldUnAlias_003_T.java
// bind_url = accuracy/fieldSensitive/objectField/FieldUnAlias_004_F
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class FieldUnAlias_004_F {
    @PostMapping(value = "FieldUnAlias_004_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        B b1 = new B();
        b1.attr = new A();
        b1.attr.b = cmd;
        B b2 = new B();
        Invoke.alias(b1, b2);
        Invoke.doUnalias(b1);
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