package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-强转表达式
 * Level X
 * Date 2024-06-28
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->强转表达式
// compose = Expression_CastExpression_001_T.java
// bind_url = completeness/base/chain/astTaint/Expression_CastExpression_001_T
// assession information end
@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Expression_CastExpression_001_T {
    @PostMapping(value = "Expression_CastExpression_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Object o1 = null;
            Object o2 = null;
            DataClass d1 = new DataClass();
            DataClass d2 = null;
            DataClass d3 = null;
            o1 = d1;
            d2 = (DataClass) o1;
            d2.data = cmd;
            o2 = o1;
            if (o2 instanceof DataClass) {
                d3 = (DataClass) o2;
                Runtime.getRuntime().exec(d3.data);
            }
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    private static class DataClass {
        public String data;
        public DataClass next;
    }
}
