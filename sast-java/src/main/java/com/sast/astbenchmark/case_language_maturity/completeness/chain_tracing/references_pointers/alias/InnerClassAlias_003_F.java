package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.references_pointers.alias;

import com.sast.astbenchmark.model.alias.Inner1b;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-InnerClassAlias-scene2
 * Level 3
 * Date 2024-07-05
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->域敏感->对象属性->对象指针->InnerClassAlias-scene2
// bind_url = completeness/chain_tracing/references_pointers/alias/InnerClassAlias_003_F
// evaluation information end
@RestController
@RequestMapping("completeness/chain_tracing/references_pointers/alias")
public class InnerClassAlias_003_F {
    @PostMapping(value = "InnerClassAlias_003_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Inner1b a = new Inner1b();
            Inner1b b = new Inner1b();

            a.obj = a.new Inner2b();
            b.obj = a.new Inner2b();

            a.obj.set(cmd);
            b.obj.set("cmd");

            String s = a.get();

            Runtime.getRuntime().exec(s);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
