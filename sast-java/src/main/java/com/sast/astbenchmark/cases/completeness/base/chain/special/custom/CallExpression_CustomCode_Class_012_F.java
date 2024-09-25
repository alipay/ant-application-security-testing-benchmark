package com.sast.astbenchmark.cases.completeness.base.chain.special.custom;

import com.sast.astbenchmark.model.custom.G;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Introduction 污点链路样本中的特殊场景-自定义函数-泛型类-wildcard
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->自定义函数调用->泛型类-wildcard
// compose = CallExpression_CustomCode_Class_011_T.java && CallExpression_CustomCode_Class_012_F.java
// bind_url = completeness/base/chain/special/CallExpression_CustomCode_Class_012_F
// assession information end

public class CallExpression_CustomCode_Class_012_F {
    @PostMapping(value = "CallExpression_CustomCode_Class_012_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        G<?> a = new G<>("ls");
        try {
            Runtime.getRuntime().exec((String) a.getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
