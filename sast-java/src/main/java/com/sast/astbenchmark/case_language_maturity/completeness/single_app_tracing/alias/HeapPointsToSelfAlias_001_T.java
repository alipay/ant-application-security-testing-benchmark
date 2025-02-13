package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.alias;

import com.sast.astbenchmark.model.alias.SimpleLinkedList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->别名->HeapPointsToSelfAlias FlowSensitive
 * Level 2
 * Date 2024-07-05
 */
@RestController
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->别名->HeapPointsToSelfAlias FlowSensitive
// bind_url = completeness/single_app_tracing/alias/HeapPointsToSelfAlias_001_T
// evaluation information end
@RequestMapping("completeness/single_app_tracing/alias")
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
