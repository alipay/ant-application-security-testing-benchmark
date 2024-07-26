package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.DataClass;
import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-过程间别名分析
 * Level X
 * Date 2024-07-02
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->对象敏感->别名是否被污染->过程间别名分析
// compose = InterproceduralAlias_001_T.java && !InterproceduralAlias_002_F.java
// bind_url = accuracy/objectSensitive/InterproceduralAlias_002_F
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class InterproceduralAlias_002_F {
    @PostMapping(value = "InterproceduralAlias_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            DataClass dc1 = new DataClass();
            Invoke.taintMe(dc1, "foo");

            Runtime.getRuntime().exec(dc1.data);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
