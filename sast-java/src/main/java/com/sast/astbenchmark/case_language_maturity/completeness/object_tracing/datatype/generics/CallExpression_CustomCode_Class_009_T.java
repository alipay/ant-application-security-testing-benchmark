package com.sast.astbenchmark.case_language_maturity.completeness.object_tracing.datatype.generics;

import com.sast.astbenchmark.model.custom.G;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Introduction 污点链路样本中的特殊场景-自定义函数-泛型类-类型参数
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->自定义函数调用->泛型类-类型参数
// compose = CallExpression_CustomCode_Class_009_T.java && !CallExpression_CustomCode_Class_010_F.java
// bind_url = completeness/base/chain/special/custom/CallExpression_CustomCode_Class_009_T
// assession information end

public class CallExpression_CustomCode_Class_009_T {
    @PostMapping(value = "CallExpression_CustomCode_Class_009_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            G<String> a = new G<>(cmd);
            Runtime.getRuntime().exec(a.getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
