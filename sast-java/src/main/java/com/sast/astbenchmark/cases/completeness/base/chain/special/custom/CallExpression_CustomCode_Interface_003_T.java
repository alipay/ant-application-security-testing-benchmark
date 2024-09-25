package com.sast.astbenchmark.cases.completeness.base.chain.special.custom;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的特殊场景-自定义函数-多重接口类型
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->自定义函数调用->多重接口类型
// compose = CallExpression_CustomCode_Interface_003_T.java && !CallExpression_CustomCode_Interface_004_F.java
// bind_url = completeness/base/chain/special/custom/CallExpression_CustomCode_Interface_003_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/special/custom")
public class CallExpression_CustomCode_Interface_003_T {
    @PostMapping(value = "CallExpression_CustomCode_Interface_003_T")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Inter s = (Serializable & Inter) (a, b) -> a + b;
            Runtime.getRuntime().exec(s.getCmd("ls", cmd));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    private interface Inter {
        String getCmd(String cmd, String cmd2);
    }
}
   