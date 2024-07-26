package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.SimpleLinkedList;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-HeapPointsToSelfAlias
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->对象敏感->别名是否被污染->HeapPointsToSelfAlias
// compose = HeapPointsToSelfAlias_001_T.java && !HeapPointsToSelfAlias_002_F.java
// bind_url = accuracy/objectSensitive/HeapPointsToSelfAlias_001_T
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
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
