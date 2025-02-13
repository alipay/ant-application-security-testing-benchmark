package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.alias;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.B;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->别名->PrimitiveFieldAccess-scene1
 * Level 2
 * Date 2024-07-02
 */
// evaluation information start
// real case = true
// evaluation item =  完整度->单应用跟踪完整度->别名->PrimitiveFieldAccess-scene1
// bind_url = completeness/single_app_tracing/references_pointers/alias/PrimitiveFieldAccess_001_T
// evaluation information end
@RestController
@RequestMapping("completeness/single_app_tracing/references_pointers/alias")
public class PrimitiveFieldAccess_001_T {
    @PostMapping(value = "PrimitiveFieldAccess_001_T")
    public Map<String, Object> testcase(@RequestParam int id) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            id = id + 100;
            B b = new B();
            A a = new A();
            b.attr = a;
            a.i = id;
            Runtime.getRuntime().exec("cat /some/path/" + b.attr.i + ".png");
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}