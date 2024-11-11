package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.exception_error.assert_statement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的语句-assert语句
 * Level 3
 * Date 2024-11-10
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->链路跟踪完整度->异常与错误处理->断言语句->assert语句
// bind_url = completeness/single_app_tracing/exception_error/assert_statement/Statement_AssertStatement_001_T/{cmd}
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/exception_error/assert_statement")
public class Statement_AssertStatement_001_T {
    @GetMapping("Statement_AssertStatement_001_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            assert cmd == null : Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
