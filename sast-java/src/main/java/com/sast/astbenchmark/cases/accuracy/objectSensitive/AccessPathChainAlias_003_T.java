package com.sast.astbenchmark.cases.accuracy.objectSensitive;

import com.sast.astbenchmark.model.alias.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象敏感-别名是否被污染-AccessPathChain-LinkedList
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->对象敏感->别名是否被污染->AccessPathChain-LinkedList
// compose = AccessPathChainAlias_003_T.java && !AccessPathChainAlias_004_F.java
// bind_url = accuracy/objectSensitive/AccessPathChainAlias_003_T
// assession information end
@RestController
@RequestMapping("accuracy/objectSensitive")
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