package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-SingleFieldAccessAlias
 * Level X
 * Date 2024-07-02
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->对象敏感->别名是否被污染->SingleFieldAccessAlias
// compose = SingleFieldAccessAlias_001_T.java && !SingleFieldAccessAlias_002_F.java
// bind_url = accuracy/objectSensitive/SingleFieldAccessAlias_001_T
// assession information end
public class SingleFieldAccessAlias_001_T {
    @PostMapping(value = "SingleFieldAccessAlias_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            A a = new A();
            a.b = "cmd";
            A b = Invoke.alias(a);
            a.b = cmd;

            Runtime.getRuntime().exec(b.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
