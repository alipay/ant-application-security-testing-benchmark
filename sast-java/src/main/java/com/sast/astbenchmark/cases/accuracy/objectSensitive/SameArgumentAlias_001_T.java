package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.B;
import com.sast.astbenchmark.model.alias.SimpleAlias;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-别名参数
 * Level X
 * Date 2024-07-02
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->对象敏感->别名是否被污染->别名参数
// compose = SameArgumentAlias_001_T.java && !SameArgumentAlias_002_F.java
// bind_url = accuracy/objectSensitive/SameArgumentAlias_001_T
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class SameArgumentAlias_001_T {
    @PostMapping(value = "SameArgumentAlias_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            SimpleAlias sa = new SimpleAlias();
            B b = new B();
            A a = new A();
            a.b = cmd;

            sa.foo(b, b);
            String s = sa.bar(a).b;

            Runtime.getRuntime().exec(s);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
