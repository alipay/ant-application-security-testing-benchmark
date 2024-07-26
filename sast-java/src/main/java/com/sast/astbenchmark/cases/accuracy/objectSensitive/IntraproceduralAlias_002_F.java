package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.B;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-过程内别名指针分析
 * Level X
 * Date 2024-07-02
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->对象敏感->别名是否被污染->过程内别名指针分析
// compose = IntraproceduralAlias_001_T.java && !IntraproceduralAlias_002_F.java
// bind_url = accuracy/objectSensitive/IntraproceduralAlias_002_F
// assession information end
public class IntraproceduralAlias_002_F {
    @PostMapping(value = "IntraproceduralAlias_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            B b = new B();
            b.attr = new A();
            A a = b.attr;
            b.attr.b = "foo";

            Runtime.getRuntime().exec(a.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
