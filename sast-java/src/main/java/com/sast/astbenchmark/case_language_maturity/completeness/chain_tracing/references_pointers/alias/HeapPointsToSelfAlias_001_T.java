package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.references_pointers.alias;

import com.sast.astbenchmark.model.alias.SimpleLinkedList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度-链路跟踪完整度-引用和指针-别名-对象属性-对象指针-HeapPointsToSelfAlias FlowSensitive
 * Level 3
 * Date 2024-07-05
 */
@RestController
// evaluation information start
// real case = true
// evaluation item = 完整度->链路跟踪完整度->引用和指针->别名->对象属性->对象指针->HeapPointsToSelfAlias FlowSensitive
// bind_url = completeness/chain_tracing/references_pointers/alias/HeapPointsToSelfAlias_001_T
// evaluation information end
@RequestMapping("completeness/chain_tracing/references_pointers/alias")
public class HeapPointsToSelfAlias_001_T {
    @PostMapping(value = "HeapPointsToSelfAlias_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            SimpleLinkedList<String> cycle = new SimpleLinkedList<>();
            SimpleLinkedList<String> x = new SimpleLinkedList<>();
            SimpleLinkedList<String> l1 = cycle;
            x.next = l1;
            l1.next = cycle;
            SimpleLinkedList<String> y = x;
            l1 = y.next;
            l1 = l1.next;
            l1 = l1.next;
            l1 = l1.next;
            l1 = l1.next;
            l1 = l1.next;
            cycle.data = cmd;
            Runtime.getRuntime().exec(l1.data);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

}
