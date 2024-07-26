package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.B;
import com.sast.astbenchmark.model.alias.StaticData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-StaticFieldAlias
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->对象敏感->别名是否被污染->FieldAlias
// compose = StaticFieldAlias_001_T.java && !StaticFieldAlias_002_F.java
// bind_url = accuracy/objectSensitive/StaticFieldAlias_001_T
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class StaticFieldAlias_001_T {
    @PostMapping(value = "StaticFieldAlias_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StaticData.staticB1 = new B();
            StaticData.staticB2 = new B();
            StaticData.staticB1 = StaticData.staticB2;
            StaticData.staticB2.attr.b = cmd;

            Runtime.getRuntime().exec(StaticData.staticB1.attr.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}