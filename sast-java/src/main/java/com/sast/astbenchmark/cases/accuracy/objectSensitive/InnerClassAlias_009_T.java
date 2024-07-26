package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.Inner1b;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-InnerClassAlias-scene4
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->对象敏感->别名是否被污染->InnerClassAlias-scene4
// compose = InnerClassAlias_009_T.java && !InnerClassAlias_010_F.java
// bind_url = accuracy/objectSensitive/InnerClassAlias_009_T
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class InnerClassAlias_009_T {
    @PostMapping(value = "InnerClassAlias_009_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Inner1b a = new Inner1b();
            a.obj = a.new Inner2b();
            a.parentData = cmd;

            Inner1b b = new Inner1b();
            b.parentData = "foo";

            a.obj = a.new Inner2b();
            Inner1b.Inner2b inner = a.obj;

            Runtime.getRuntime().exec(inner.getParent());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
