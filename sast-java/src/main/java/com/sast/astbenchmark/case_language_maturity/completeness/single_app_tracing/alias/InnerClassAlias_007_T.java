package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.alias;

import com.sast.astbenchmark.model.alias.Inner3;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->单应用跟踪完整度->别名->InnerClassAlias-scene3
 * Level 2
 * Date 2024-07-05
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->单应用跟踪完整度->别名->InnerClassAlias-scene3
// bind_url = completeness/single_app_tracing/references_pointers/alias/InnerClassAlias_007_T
// evaluation information end
@RestController
@RequestMapping("completeness/single_app_tracing/references_pointers/alias")
public class InnerClassAlias_007_T {
    @PostMapping(value = "InnerClassAlias_007_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Inner3 a = new Inner3();
            Inner3 b = new Inner3();

            a.obj2 = b.new Inner2b();
            a.obj2.foo = b.new Inner2();

            a.obj2.foo.set(cmd);
            String s = b.get(); // tainted

            Runtime.getRuntime().exec(s);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
