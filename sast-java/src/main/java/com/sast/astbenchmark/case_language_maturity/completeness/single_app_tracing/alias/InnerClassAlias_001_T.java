package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.alias;

import com.sast.astbenchmark.model.alias.Inner1;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-InnerClassAlias-scene1
 * Level 3
 * Date 2024-07-05
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->域敏感->对象属性->对象指针->InnerClassAlias-scene1
// bind_url = completeness/single_app_tracing/references_pointers/alias/InnerClassAlias_001_T
// evaluation information end
@RestController
@RequestMapping("completeness/single_app_tracing/references_pointers/alias")
public class InnerClassAlias_001_T {
    @PostMapping(value = "InnerClassAlias_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Inner1 a = new Inner1();
            Inner1 b = new Inner1();

            a.obj = a.new Inner2();
            b.obj = a.new Inner2();

            a.obj.set(cmd);
            String s = a.get(); // tainted

            Runtime.getRuntime().exec(s);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
