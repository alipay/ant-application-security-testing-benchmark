package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.B;
import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-FieldUnAlias
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->对象敏感->别名是否被污染->FieldUnAlias
// compose = !FieldUnAlias_001_F.java && FieldUnAlias_002_T.java
// bind_url = accuracy/objectSensitive/FieldUnAlias_002_T
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
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
