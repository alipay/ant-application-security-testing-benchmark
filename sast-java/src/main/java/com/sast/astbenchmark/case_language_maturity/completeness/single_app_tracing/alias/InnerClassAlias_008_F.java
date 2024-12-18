package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.alias;

import com.sast.astbenchmark.model.alias.Inner3;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-InnerClassAlias-scene3
 * Level 3
 * Date 2024-07-05
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->域敏感->对象属性->对象指针->InnerClassAlias-scene3
// bind_url = completeness/single_app_tracing/references_pointers/alias/InnerClassAlias_008_F
// evaluation information end
@RestController
@RequestMapping("completeness/single_app_tracing/references_pointers/alias")
public class InnerClassAlias_008_F {
    @PostMapping(value = "InnerClassAlias_008_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Inner3 a = new Inner3();
            Inner3 b = new Inner3();

            a.obj2 = b.new Inner2b();
            a.obj2.foo = b.new Inner2();

            a.obj2.foo.set(cmd);
            String s = a.get(); // untainted

            Runtime.getRuntime().exec(s);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}