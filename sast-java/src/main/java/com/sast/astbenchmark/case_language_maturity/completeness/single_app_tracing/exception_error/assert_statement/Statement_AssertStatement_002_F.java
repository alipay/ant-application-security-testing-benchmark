package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.exception_error.assert_statement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->异常与错误处理->断言
// scene introduction = assert语句
// level = 2+
// bind_url = completeness/single_app_tracing/exception_error/assert_statement/Statement_AssertStatement_002_F/{cmd}
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/exception_error/assert_statement")
public class Statement_AssertStatement_002_F {
    @GetMapping("Statement_AssertStatement_002_F/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            assert cmd != null : Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
