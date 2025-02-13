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
 * Introduction 完整度->单应用跟踪完整度->别名->ReturnAlias FlowSensitive-scene2
 * Level 2
 * Date 2024-07-02
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->别名->ReturnAlias FlowSensitive-scene2
// bind_url = completeness/single_app_tracing/references_pointers/alias/ReturnAlias_004_F
// evaluation information end
@RestController
@RequestMapping("completeness/single_app_tracing/references_pointers/alias")
public class ReturnAlias_004_F {
    @PostMapping(value = "ReturnAlias_004_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            A a = new A();
            A b = Invoke.alias(a);
            A c = Invoke.alias(b);
            c.b = cmd;
            A d = Invoke.alias(c);
            A e = Invoke.alias(d);
            a.b = "foo";

            Runtime.getRuntime().exec(e.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
