package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.Inner3;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-InnerClassAlias-scene3
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->对象敏感->别名是否被污染->InnerClassAlias-scene3
// compose = !InnerClassAlias_008_F.java && InnerClassAlias_007_T.java
// bind_url = accuracy/objectSensitive/InnerClassAlias_007_T
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class InnerClassAlias_007_T {
    @PostMapping(value = "InnerClassAlias_007_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Inner3 a = new Inner3();
            Inner3 b = new Inner3();

            a.obj2 = b.new Inner2b();
            a.obj2.foo = b.new Inner2();

            a.obj2.foo.set(cmd);
            String s = b.get(); // tainted

            Runtime.getRuntime().exec(s);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
