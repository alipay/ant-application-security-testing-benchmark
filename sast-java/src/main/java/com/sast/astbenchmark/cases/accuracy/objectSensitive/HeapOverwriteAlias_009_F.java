package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.J;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-HeapOverwriteAlias-scene4
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->对象敏感->别名是否被污染->HeapOverwriteAlias-scene4
// compose = HeapOverwriteAlias_007_T.java && HeapOverwriteAlias_008_T.java && !HeapOverwriteAlias_009_F.java
// bind_url = accuracy/objectSensitive/HeapOverwriteAlias_009_F
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class HeapOverwriteAlias_009_F {
    @PostMapping(value = "HeapOverwriteAlias_009_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            J j = new J();
            j.read();
            j.f.str = "";
            Runtime.getRuntime().exec(j.e.str);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
