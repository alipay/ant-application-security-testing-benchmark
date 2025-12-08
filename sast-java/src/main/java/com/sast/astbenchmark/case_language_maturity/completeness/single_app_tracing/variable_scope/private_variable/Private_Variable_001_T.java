package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.variable_scope.private_variable;

import com.sast.astbenchmark.common.utils.SinkUtil;
import com.sast.astbenchmark.model.custom.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->变量作用域->静态变量
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/private_variable/Private_Variable_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/variable_scope/private_variable")
public class Private_Variable_001_T {
    @PostMapping(value = "private_variable_001_T")
    public Map<String, Object> private_variable_001_t(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        P p = new P(cmd);
        try {
            SinkUtil.sink(p.getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}