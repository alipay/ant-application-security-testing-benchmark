package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.references_pointers.alias;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction  完整度-链路跟踪完整度-引用和指针-别名-对象指针-SingleFieldAccessAlias
 * Level 3
 * Date 2024-07-02
 */
// evaluation information start
// real case = false
// evaluation item =  完整度->链路跟踪完整度->引用和指针->别名->对象属性->对象指针->SingleFieldAccessAlias
// bind_url = completeness/chain_tracing/references_pointers/alias/SingleFieldAccessAlias_002_F
// evaluation information end
@RestController
@RequestMapping("completeness/chain_tracing/references_pointers/alias")
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
