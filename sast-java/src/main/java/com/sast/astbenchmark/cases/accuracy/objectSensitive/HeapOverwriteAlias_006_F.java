package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.H;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-HeapOverwriteAlias-scene3
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->对象敏感->别名是否被污染->HeapOverwriteAlias-scene3
// compose = !HeapOverwriteAlias_006_F.java && HeapOverwriteAlias_005_T.java
// bind_url = accuracy/objectSensitive/HeapOverwriteAlias_006_F
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class HeapOverwriteAlias_006_F {
    @PostMapping(value = "HeapOverwriteAlias_006_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            H h = new H();
            h.read();
            Runtime.getRuntime().exec(h.i.str2);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
