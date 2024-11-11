package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.references_pointers.alias;

import com.sast.astbenchmark.model.alias.A;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度-链路跟踪完整度-引用和指针-别名-对象属性-对象指针-HeapOverwriteAlias FlowSensitive-scene2
 * Level 3
 * Date 2024-07-05
 */
@RestController
// evaluation information start
// real case = true
// evaluation item = 完整度->链路跟踪完整度->引用和指针->别名->对象属性->对象指针->HeapOverwriteAlias-scene2
// bind_url = completeness/chain_tracing/references_pointers/alias/HeapOverwriteAlias_003_T
// evaluation information end
@RequestMapping("completeness/chain_tracing/references_pointers/alias")
public class HeapOverwriteAlias_003_T {
    @PostMapping(value = "HeapOverwriteAlias_003_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            A a1 = new A();
            A a2 = a1;
            a1.b = cmd;

            Runtime.getRuntime().exec(a1.b);
            a2.b = "Y";
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
