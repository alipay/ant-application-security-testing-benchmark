package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * Introduction 污点链路样本中的表达式-变量传递通过native方法发生在两个入参上
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->变量传递通过native方法发生在两个入参上
// compose = Expression_AssignmentExpression_001_T.java
// bind_url = completeness/base/chain/astTaint/Expression_AssignmentExpression_001_T/{cmd}
// assession information end
@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Expression_AssignmentExpression_001_T {
    @PostMapping("Expression_AssignmentExpression_001_T/{cmd}")
    public Map<String, Object> aTaintCase018(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            String[] b = {"a","b"};
            System.arraycopy(cmd,0,b,0,2);
            Runtime.getRuntime().exec(b[0]);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
