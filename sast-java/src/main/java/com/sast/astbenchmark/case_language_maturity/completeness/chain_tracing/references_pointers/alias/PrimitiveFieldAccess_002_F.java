package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.references_pointers.alias;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.B;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度-链路跟踪完整度-引用和指针-别名-对象指针-PrimitiveFieldAccess-scene1
 * Level 3
 * Date 2024-07-02
 */
@RestController
// evaluation information start
// real case = false
// evaluation item = 
// bind_url = completeness/chain_tracing/references_pointers/alias/PrimitiveFieldAccess_002_F
// evaluation information end
@RequestMapping("completeness/chain_tracing/references_pointers/alias")
public class PrimitiveFieldAccess_002_F {
    @PostMapping(value = "PrimitiveFieldAccess_002_F")
    public Map<String, Object> testcase(@RequestParam int id) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            id = 0x1d + 100;
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