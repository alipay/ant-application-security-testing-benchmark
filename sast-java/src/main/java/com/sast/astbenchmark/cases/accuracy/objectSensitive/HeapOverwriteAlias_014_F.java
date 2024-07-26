package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.B;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-HeapOverwriteAlias-scene6
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->对象敏感->别名是否被污染->HeapOverwriteAlias-scene6
// compose = HeapOverwriteAlias_013_T.java && !HeapOverwriteAlias_014_F.java
// bind_url = accuracy/objectSensitive/HeapOverwriteAlias_014_F
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class HeapOverwriteAlias_014_F {
    @PostMapping(value = "HeapOverwriteAlias_014_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            B b = new B();
            b.attr.b = cmd;

            b = new B();

            Runtime.getRuntime().exec(b.attr.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
