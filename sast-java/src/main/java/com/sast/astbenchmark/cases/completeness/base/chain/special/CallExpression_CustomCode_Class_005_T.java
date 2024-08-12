package com.sast.astbenchmark.cases.completeness.base.chain.special;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Introduction 污点链路样本中的特殊场景-自定义函数调用-泛型类 type parameters
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->自定义函数调用->泛型类 type parameters
// compose = CallExpression_CustomCode_Class_005_T.java
// bind_url = completeness/base/chain/special/CallExpression_CustomCode_Class_005_T
// assession information end

public class CallExpression_CustomCode_Class_005_T {
    @PostMapping(value = "CallExpression_CustomCode_Class_005_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        Generic<String> a = new Generic<>(cmd);
        try {
            Runtime.getRuntime().exec(a.getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}

class Generic<T> {
    private T cmd;

    public Generic(T cmd) {
        this.cmd = cmd;
    }

    public T getCmd() {
        return this.cmd;
    }
}