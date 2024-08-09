package com.sast.astbenchmark.cases.accuracy.contextSensitive;

import com.sast.astbenchmark.model.alias.Invoke;
import com.sast.astbenchmark.model.alias.X;
import com.sast.astbenchmark.model.alias.Y;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 上下文敏感-HeapAllocSite
 * Level X
 * Date 2024-07-05
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->上下文敏感->HeapAllocSite
// compose = HeapAllocSite_001_T.java && !HeapAllocSite_002_F.java
// bind_url = accuracy/contextSensitive/HeapAllocSite_001_T
// assession information end
@RestController
@RequestMapping("accuracy/contextSensitive")
public class HeapAllocSite_001_T {
    @PostMapping(value = "HeapAllocSite_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            X x1 = new X();
            X x2 = new X();
            Y y1 = new Y();
            Y y2 = new Y();
            y1.f = cmd;
            y2.f = "foo";
            X rx1 = Invoke.assign(x1, y1);
            X rx2 = Invoke.assign(x2, y2);
            Runtime.getRuntime().exec(rx1.y.f);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
