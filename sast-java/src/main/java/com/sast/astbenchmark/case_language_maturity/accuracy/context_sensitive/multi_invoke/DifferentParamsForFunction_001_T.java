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
// evaluation information start
// real case = true
// evaluation item =  准确度->上下文敏感分析->多次调用
// scene introduction = HeapAllocSite
// level = 2
// bind_url = accuracy/context_sensitive/multi_invoke/DifferentParamsForFunction_001_T
// evaluation information end

@RestController
@RequestMapping("accuracy/context_sensitive/multi_invoke")
public class DifferentParamsForFunction_001_T {
    @PostMapping(value = "DifferentParamsForFunction_001_T")
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
