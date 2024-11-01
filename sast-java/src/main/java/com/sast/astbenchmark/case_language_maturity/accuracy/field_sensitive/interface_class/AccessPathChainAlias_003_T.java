package com.sast.astbenchmark.case_language_maturity.accuracy.field_sensitive.interface_class;

import com.sast.astbenchmark.model.alias.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 域敏感-对象属性-对象指针-AccessPathChain-LinkedList
 * Level 3
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->域敏感->对象属性->对象指针->AccessPathChain-LinkedList
// compose = AccessPathChainAlias_003_T.java && !AccessPathChainAlias_004_F.java
// bind_url = accuracy/fieldSensitive/objectField/AccessPathChainAlias_003_T
// assession information end
@RestController
@RequestMapping("accuracy/fieldSensitive/objectField")
public class AccessPathChainAlias_003_T {
    @PostMapping(value = "AccessPathChainAlias_003_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            A a = new A();
            SimpleLinkedList<A> list = Invoke.newSimpleLinkedList(a);
            a.b = cmd;
            A last = list.next.next.next.next.next.next.data;
            Runtime.getRuntime().exec(last.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}