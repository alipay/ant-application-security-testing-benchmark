package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.alias;

import com.sast.astbenchmark.model.alias.B;
import com.sast.astbenchmark.model.alias.StaticData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->别名
// scene introduction = FieldAlias FlowSensitive
// level = 2
// bind_url = completeness/single_app_tracing/alias/StaticFieldAlias_001_T
// evaluation information end
@RequestMapping("completeness/single_app_tracing/alias")
public class StaticFieldAlias_001_T {
    @PostMapping(value = "StaticFieldAlias_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StaticData.staticB1 = new B();
            StaticData.staticB2 = new B();
            StaticData.staticB1 = StaticData.staticB2;
            StaticData.staticB2.attr.b = cmd;

            Runtime.getRuntime().exec(StaticData.staticB1.attr.b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}