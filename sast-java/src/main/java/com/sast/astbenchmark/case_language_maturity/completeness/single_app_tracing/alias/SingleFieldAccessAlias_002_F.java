package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.alias;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->别名->SingleFieldAccessAlias
 * Level 2
 * Date 2024-07-02
 */
// evaluation information start
// real case = false
// evaluation item =  完整度->单应用跟踪完整度->别名
// bind_url = completeness/single_app_tracing/alias/SingleFieldAccessAlias_002_F
// evaluation information end
@RestController
@RequestMapping("completeness/single_app_tracing/alias")
public class SingleFieldAccessAlias_002_F {
    @PostMapping(value = "SingleFieldAccessAlias_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            A a = new A();
            a.b = cmd;
            A b = Invoke.alias(a);
            a.b = "cmd";

            Runtime.getRuntime().exec(b.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
