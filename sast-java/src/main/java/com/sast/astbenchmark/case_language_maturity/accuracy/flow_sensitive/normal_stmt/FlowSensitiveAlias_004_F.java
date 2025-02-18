package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.normal_stmt;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.B;
import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->流敏感->常规顺序执行语句->对象属性->对象指针->流敏感指针分析-过程间分析-scene2
 * Level 2
 * Date 2024-07-02
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感->常规顺序执行语句
// bind_url = accuracy/flow_sensitive/normal_stmt/FlowSensitiveAlias_004_F
// evaluation information end
@RestController
@RequestMapping("accuracy/flow_sensitive/normal_stmt")
public class FlowSensitiveAlias_004_F {
    @PostMapping(value = "FlowSensitiveAlias_004_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            A a = new A();
            B b = new B();
            Invoke.alias(b, a);
            Runtime.getRuntime().exec(b.attr.b);
            a.b = cmd;
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
