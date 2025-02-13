package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.alias;

import com.sast.astbenchmark.model.alias.A;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->别名->HeapOverwriteAlias-scene1
 * Level 2
 * Date 2024-07-05
 */
// evaluation information start
// real case = false
// evaluation item =完整度->单应用跟踪完整度->别名->HeapOverwriteAlias-scene1
// bind_url = completeness/single_app_tracing/alias/HeapOverwriteAlias_002_F
// evaluation information end
@RestController
@RequestMapping("completeness/single_app_tracing/alias")
public class HeapOverwriteAlias_002_F {
    @PostMapping(value = "HeapOverwriteAlias_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            A a1 = new A();
            A a2 = a1;
            a1.b = cmd;
            a2 = new A();

            Runtime.getRuntime().exec(a2.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
