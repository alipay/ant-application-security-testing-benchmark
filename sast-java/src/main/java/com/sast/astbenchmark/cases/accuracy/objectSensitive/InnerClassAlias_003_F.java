package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.Inner1;
import com.sast.astbenchmark.model.alias.Inner1b;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-InnerClassAlias-scene2
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->对象敏感->别名是否被污染->InnerClassAlias-scene2
// compose = !InnerClassAlias_003_F.java && !InnerClassAlias_004_F.java && InnerClassAlias_005_T.java && InnerClassAlias_006_T.java
// bind_url = accuracy/objectSensitive/InnerClassAlias_003_F
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class InnerClassAlias_003_F {
    @PostMapping(value = "InnerClassAlias_003_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Inner1b a = new Inner1b();
            Inner1b b = new Inner1b();

            a.obj = a.new Inner2b();
            b.obj = a.new Inner2b();

            a.obj.set(cmd);
            b.obj.set("cmd");

            String s = a.get();

            Runtime.getRuntime().exec(s);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
