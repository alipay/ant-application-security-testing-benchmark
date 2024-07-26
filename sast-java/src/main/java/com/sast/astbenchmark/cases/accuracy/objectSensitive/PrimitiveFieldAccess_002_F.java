package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.B;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-PrimitiveFieldAccess-scene1
 * Level X
 * Date 2024-07-02
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->对象敏感->别名是否被污染->PrimitiveFieldAccess-scene1
// compose = PrimitiveFieldAccess_001_T.java && !PrimitiveFieldAccess_002_F.java
// bind_url = accuracy/objectSensitive/PrimitiveFieldAccess_002_F
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class PrimitiveFieldAccess_002_F {
    @PostMapping(value = "PrimitiveFieldAccess_002_F")
    public Map<String, Object> testcase(@RequestParam int id) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            id = 0x1d + 100;
            B b = new B();
            A a = new A();
            b.attr = a;
            a.i = id;
            Runtime.getRuntime().exec("cat /some/path/" + b.attr.i + ".png");
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}