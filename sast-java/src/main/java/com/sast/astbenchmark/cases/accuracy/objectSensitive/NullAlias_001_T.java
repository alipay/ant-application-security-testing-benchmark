package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.DataClass;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-NullAlias
 * Level X
 * Date 2024-07-02
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->对象敏感->别名是否被污染->NullAlias
// compose = NullAlias_001_T.java && !NullAlias_002_F.java
// bind_url = accuracy/objectSensitive/NullAlias_001_T
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
public class NullAlias_001_T {
    @PostMapping(value = "NullAlias_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            DataClass dc = new DataClass();
            DataClass dc2 = null;
            DataClass dc3 = new DataClass();

            dc2 = dc3;

            dc2.next = dc;
            dc2 = null;

            String a = cmd;
            dc.data = a;
            dc = null;

            Runtime.getRuntime().exec(dc3.next.data);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
