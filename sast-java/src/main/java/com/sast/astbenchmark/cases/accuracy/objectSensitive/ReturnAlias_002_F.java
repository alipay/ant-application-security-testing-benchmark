package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.B;
import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-ReturnAlias-scene1
 * Level X
 * Date 2024-07-02
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->对象敏感->别名是否被污染->ReturnAlias-scene1
// compose = ReturnAlias_001_T.java && !ReturnAlias_002_F.java
// bind_url = accuracy/objectSensitive/ReturnAlias_002_F
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class ReturnAlias_002_F {
    @PostMapping(value = "ReturnAlias_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String tainted = cmd;
            B b = new B();
            b.attr.b = tainted;
            B c = b;
            A a = Invoke.alias(c);
            c.attr.b = "bar";

            Runtime.getRuntime().exec(a.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
