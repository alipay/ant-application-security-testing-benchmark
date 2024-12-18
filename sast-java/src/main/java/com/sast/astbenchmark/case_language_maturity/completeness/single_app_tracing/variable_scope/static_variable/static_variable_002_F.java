package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.variable_scope.static_variable;

import com.sast.astbenchmark.common.utils.SinkUtil;
import com.sast.astbenchmark.model.custom.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->污点对象跟踪完整度->变量->静态变量
 * Level 2
 * Date 2024-08-16
 */

// evaluation information start
// real case = false
// evaluation item = 完整度->污点对象跟踪完整度->变量->静态变量
// bind_url = completeness/single_app_tracing/variable_scope/static_variable/static_variable_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/variable_scope/static_variable")
public class static_variable_002_F {
    @PostMapping(value = "static_variable_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            SinkUtil.sink(P.t);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}