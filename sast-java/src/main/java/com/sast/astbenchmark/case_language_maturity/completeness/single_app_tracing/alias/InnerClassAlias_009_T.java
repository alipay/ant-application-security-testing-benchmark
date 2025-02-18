package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.alias;

import com.sast.astbenchmark.model.alias.Inner1b;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->别名->InnerClassAlias-scene4
 * Level 2
 * Date 2024-07-05
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->别名
// bind_url = completeness/single_app_tracing/alias/InnerClassAlias_009_T
// evaluation information end
@RestController
@RequestMapping("completeness/single_app_tracing/alias")
public class InnerClassAlias_009_T {
    @PostMapping(value = "InnerClassAlias_009_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Inner1b a = new Inner1b();
            a.obj = a.new Inner2b();
            a.parentData = cmd;

            Inner1b b = new Inner1b();
            b.parentData = "foo";

            a.obj = a.new Inner2b();
            Inner1b.Inner2b inner = a.obj;

            Runtime.getRuntime().exec(inner.getParent());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
