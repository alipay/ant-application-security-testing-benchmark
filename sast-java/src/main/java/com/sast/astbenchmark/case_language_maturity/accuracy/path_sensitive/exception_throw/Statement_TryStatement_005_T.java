package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.exception_throw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->异常抛出和捕获
// scene introduction = 涉及求解问题->try语句-数组越界异常or抛出异常
// level = 3
// bind_url = accuracy/path_sensitive/exception_throw/Statement_TryStatement_005_T/{cmd}
// evaluation information end
@RestController()
@RequestMapping("accuracy/path_sensitive/exception_throw")
public class Statement_TryStatement_005_T {
    @GetMapping("Statement_TryStatement_005_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            int[] data = new int[10];
            throw new IOException(cmd);
        } catch (IndexOutOfBoundsException | IOException ex) {
            try {
                Runtime.getRuntime().exec(ex.getMessage());
                modelMap.put("status", "success");
            } catch (Exception e) {
                modelMap.put("status", "error");
            }
        }
        return modelMap;
    }
}
