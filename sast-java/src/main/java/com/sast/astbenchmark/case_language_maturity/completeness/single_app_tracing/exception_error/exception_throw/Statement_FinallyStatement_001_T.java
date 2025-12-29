package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.exception_error.exception_throw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->异常抛出和捕获
// scene introduction = try语句-finally块
// level = 2
// bind_url = completeness/single_app_tracing/exception_error/exception_throw/Statement_FinallyStatement_001_T
// evaluation information end
@RestController
@RequestMapping("completeness/single_app_tracing/exception_error/exception_throw")
public class Statement_FinallyStatement_001_T {
    @GetMapping("Statement_FinallyStatement_001_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {

        } catch (Exception e) {

        } finally {
            try {
                Runtime.getRuntime().exec(cmd);
                modelMap.put("status", "success");
            } catch (Exception e) {
                modelMap.put("status", "error");
            }
        }
        return modelMap;
    }
}