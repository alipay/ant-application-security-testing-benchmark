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
 * Introduction 对象敏感-别名是否被污染-流敏感指针分析
 * Level X
 * Date 2024-07-02
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->对象敏感->别名是否被污染->流敏感指针分析
// compose = !FlowSensitiveAlias_004_F.java && FlowSensitiveAlias_003_T.java
// bind_url = accuracy/objectSensitive/FlowSensitiveAlias_004_F
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class FlowSensitiveAlias_004_F {
    @PostMapping(value = "FlowSensitiveAlias_004_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            A a = new A();
            B b = new B();
            Invoke.alias(b, a);
            Runtime.getRuntime().exec(b.attr.b);
            a.b = cmd;
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
