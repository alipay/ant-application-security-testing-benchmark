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
// evaluation information start
// real case = false
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->特殊场景->自定义函数调用->泛型类-类型参数
// bind_url = completeness/object_tracing/interface_class/anonymous_object/CallExpression_CustomCode_Class_010_F
// evaluation information end
public class CallExpression_CustomCode_Class_010_F {
    @PostMapping(value = "CallExpression_CustomCode_Class_010_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            G<String> a = new G<>("ls");
            Runtime.getRuntime().exec(a.getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
