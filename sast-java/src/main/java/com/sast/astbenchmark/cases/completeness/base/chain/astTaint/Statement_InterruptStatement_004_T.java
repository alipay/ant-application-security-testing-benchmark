package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的语句-中断语句continue+label
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->中断语句continue+label
// compose = Statement_InterruptStatement_004_T.java
// bind_url = completeness/base/chain/astTaint/Statement_InterruptStatement_004_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Statement_InterruptStatement_004_T {
    @GetMapping("Statement_InterruptStatement_004_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a ="mkdir";
            outerLoop:
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    a = cmd + "|";
                    if (i == 1 && j == 1) {
                        a = "ls";
                        continue outerLoop;
                    }
                }
            }
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
