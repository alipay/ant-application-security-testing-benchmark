package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.exception_error.exception_throw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction ->try语句-finally块
 * Level 4
 * Date 2024-11-10
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->链路跟踪完整度->异常与错误处理->异常处理->try语句-finally块
// bind_url = completeness/single_app_tracing/exception_error/exception_throw/Statement_FinallyStatement_002_F/{cmd}
// evaluation information end
@RestController
@RequestMapping("completeness/single_app_tracing/exception_error/exception_throw")
public class Statement_FinallyStatement_002_F {
    @GetMapping("Statement_FinallyStatement_002_F/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {

        } catch (Exception e) {

        } finally {
            try {
                String a = "ls";
                Runtime.getRuntime().exec(a);
                modelMap.put("status", "success");
            } catch (Exception e) {
                modelMap.put("status", "error");
            }
        }
        return modelMap;
    }
}