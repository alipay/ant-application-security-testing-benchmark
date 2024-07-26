package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.B;
import com.sast.astbenchmark.model.alias.C;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-HeapOverwriteAlias-ThreeAccessPath
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->对象敏感->别名是否被污染->HeapOverwriteAlias-ThreeAccessPath
// compose = HeapOverwriteAlias_017_T.java && !HeapOverwriteAlias_018_F.java
// bind_url = accuracy/objectSensitive/HeapOverwriteAlias_018_F
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class HeapOverwriteAlias_018_F {
    @PostMapping(value = "HeapOverwriteAlias_018_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            C c = new C();
            c.b = new B();
            c.b.attr = new A();

            A a = c.b.attr;
            a.b = cmd;
            c.b = new B();
            Runtime.getRuntime().exec(c.b.attr.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}