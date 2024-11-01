package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.references_pointers.alias;

import com.sast.astbenchmark.model.alias.B;
import com.sast.astbenchmark.model.alias.StaticData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction  完整度-链路跟踪完整度-引用和指针-别名-对象属性-对象指针-StaticFieldAlias FlowSensitive
 * Level 3
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->链路跟踪完整度->引用和指针->别名->对象属性->对象指针->FieldAlias FlowSensitive
// compose = StaticFieldAlias_001_T.java && !StaticFieldAlias_002_F.java
// bind_url = accuracy/fieldSensitive/objectField/StaticFieldAlias_001_T
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class StaticFieldAlias_001_T {
    @PostMapping(value = "StaticFieldAlias_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StaticData.staticB1 = new B();
            StaticData.staticB2 = new B();
            StaticData.staticB1 = StaticData.staticB2;
            StaticData.staticB2.attr.b = cmd;

            Runtime.getRuntime().exec(StaticData.staticB1.attr.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}