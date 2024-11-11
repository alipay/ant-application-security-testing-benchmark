package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.exception_error.assert_statement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的语句-assert语句-错误表达式sink
 * Level 3
 * Date 2024-08-16
 */

// evaluation information start
// real case = false
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->ast对象->assert语句-错误表达式sink
// bind_url = completeness/chain_tracing/exception_error/assert_statement/Statement_AssertStatement_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/exception_error/assert_statement")
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
