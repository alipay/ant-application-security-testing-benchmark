package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.override;

import com.sast.astbenchmark.model.custom.PS;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
// scene introduction = 多态
// level = 2
// bind_url = completeness/single_app_tracing/function_call/override/Expression_Override_001_T/{cmd}
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/override")
public class Expression_Override_001_T {
    @GetMapping("Expression_Override_001_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            PS ps = new PS(cmd, "~");
            Runtime.getRuntime().exec(ps.getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
