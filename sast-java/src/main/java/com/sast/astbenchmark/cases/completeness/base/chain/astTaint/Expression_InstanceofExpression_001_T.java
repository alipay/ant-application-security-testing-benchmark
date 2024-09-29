package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-instanceof表达式
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->instanceof表达式
// compose = Expression_InstanceofExpression_001_T.java && !Expression_InstanceofExpression_002_F.java
// bind_url = completeness/base/chain/astTaint/Expression_InstanceofExpression_001_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Expression_InstanceofExpression_001_T {
    @GetMapping("Expression_InstanceofExpression_001_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable Object cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            if (cmd instanceof String) {
                String data = (String) cmd;
                Runtime.getRuntime().exec(data);
                modelMap.put("status", "success");
            } else {
                modelMap.put("status", "error");
            }
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
