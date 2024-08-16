package com.sast.astbenchmark.cases.completeness.base.chain.special;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Introduction 污点链路样本中的特殊场景-自定义函数调用-泛型类
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->自定义函数调用->泛型类
// compose = CallExpression_CustomCode_Class_005_T.java && CallExpression_CustomCode_Class_006_T.java
// bind_url = completeness/base/chain/special/CallExpression_CustomCode_Class_006_T
// assession information end

public class CallExpression_CustomCode_Class_006_T {
    @PostMapping(value = "CallExpression_CustomCode_Class_006_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        GenericC<?> a = new GenericC<>(cmd);
        try {
            Runtime.getRuntime().exec((String) a.getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}

class GenericC<T> {
    private T cmd;

    public GenericC(T cmd) {
        this.cmd = cmd;
    }

    public T getCmd() {
        return this.cmd;
    }
}