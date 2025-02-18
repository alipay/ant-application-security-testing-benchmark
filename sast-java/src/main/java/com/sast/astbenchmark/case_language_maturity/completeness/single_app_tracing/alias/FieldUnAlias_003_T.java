package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.alias;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.B;
import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->别名->FieldUnAlias scene2
 * Level 2
 * Date 2024-07-05
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->别名
// bind_url = completeness/single_app_tracing/alias/FieldUnAlias_003_T
// evaluation information end
@RestController
@RequestMapping("completeness/single_app_tracing/alias")
public class FieldUnAlias_003_T {
    @PostMapping(value = "FieldUnAlias_003_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        B b1 = new B();
        b1.attr = new A();
        b1.attr.b = cmd;
        B b2 = new B();
        Invoke.alias(b1, b2);
        Invoke.doUnalias(b2);
        b2.attr.b = "bar";
        try {
            Runtime.getRuntime().exec(b1.attr.b);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}