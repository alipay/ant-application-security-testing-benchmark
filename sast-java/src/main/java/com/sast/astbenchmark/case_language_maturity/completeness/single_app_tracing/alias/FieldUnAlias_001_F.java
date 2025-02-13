package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.alias;

import com.sast.astbenchmark.model.alias.B;
import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->别名->FieldUnAlias-scene1
 * Level 2
 * Date 2024-07-05
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->别名->FieldUnAlias-scene1
// bind_url = completeness/single_app_tracing/references_pointers/alias/FieldUnAlias_001_F
// evaluation information end
@RestController
@RequestMapping("completeness/single_app_tracing/references_pointers/alias")
public class FieldUnAlias_001_F {
    @PostMapping(value = "FieldUnAlias_001_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        B b1 = new B();
        B b2 = new B();

        b2.attr = b1.attr;
        Invoke.doUnalias(b2);
        b1.attr.b = cmd;

        try {
            Runtime.getRuntime().exec(b2.attr.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
