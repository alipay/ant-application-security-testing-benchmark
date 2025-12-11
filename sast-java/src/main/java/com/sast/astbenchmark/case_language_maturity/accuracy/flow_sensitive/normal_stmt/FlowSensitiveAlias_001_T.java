package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.normal_stmt;

import com.sast.astbenchmark.model.alias.DataClass;
import com.sast.astbenchmark.model.alias.Invoke;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->常规顺序执行语句
// scene introduction = 对象属性->对象指针->流敏感指针分析-过程间分析-scene1
// level = 2
// bind_url = accuracy/flow_sensitive/normal_stmt/FlowSensitiveAlias_001_T
// evaluation information end
@RestController
@RequestMapping("accuracy/flow_sensitive/normal_stmt")
public class FlowSensitiveAlias_001_T {
    @PostMapping(value = "FlowSensitiveAlias_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            DataClass dc1 = new DataClass();
            DataClass dc2 = new DataClass();
            Invoke.taintMe(dc1, cmd);
            Invoke.copy(dc1, dc2);

            Runtime.getRuntime().exec(dc2.data);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
