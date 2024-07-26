package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.D;
import com.sast.astbenchmark.model.alias.E;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-HeapOverwriteAlias-scene5
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->对象敏感->别名是否被污染->HeapOverwriteAlias-scene5
// compose = HeapOverwriteAlias_010_T.java && HeapOverwriteAlias_011_T.java && !HeapOverwriteAlias_012_F.java
// bind_url = accuracy/objectSensitive/HeapOverwriteAlias_012_F
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class HeapOverwriteAlias_012_F {
    @PostMapping(value = "HeapOverwriteAlias_012_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            D d = new D();
            E e = new E();
            E e2 = new E();
            d.e = e2;
            d.setF(e);
            Runtime.getRuntime().exec(e2.str);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
