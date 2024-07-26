package com.sast.astbenchmark.cases.accuracy.objectSensitive;

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
 * Introduction 对象敏感-别名是否被污染-FieldUnAlias
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->对象敏感->别名是否被污染->FieldUnAlias
// compose = !FieldUnAlias_004_F.java && FieldUnAlias_003_T.java
// bind_url = accuracy/objectSensitive/FieldUnAlias_004_F
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
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