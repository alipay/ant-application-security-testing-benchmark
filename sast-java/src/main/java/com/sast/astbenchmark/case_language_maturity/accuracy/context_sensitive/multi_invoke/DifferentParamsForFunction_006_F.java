package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.multi_invoke;

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
 * Introduction 准确度->上下文敏感分析->多次调用->HeapAllocSite
 * Level 2
 * Date 2024-07-05
 */
// evaluation information start
// real case = false
// evaluation item =  准确度->上下文敏感分析->多次调用
// bind_url = accuracy/context_sensitive/multi_invoke/DifferentParamsForFunction_006_F
// evaluation information end
@RestController
@RequestMapping("accuracy/context_sensitive/multi_invoke")
public class DifferentParamsForFunction_006_F {
    @PostMapping(value = "DifferentParamsForFunction_006_F")
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
            Runtime.getRuntime().exec(rx2.y.f);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
