package com.sast.astbenchmark.cases.completeness.base.chain.special;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的特殊场景-无源码函数-string getBytes+ByteArrayOutputStream
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->无源码函数调用->string.getBytes+ByteArrayOutputStream
// compose = CallExpression_NoSourceCode_Native_034_T.java
// bind_url = completeness/base/chain/special/CallExpression_NoSourceCode_Native_034_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/special")
public class CallExpression_NoSourceCode_Native_034_T {
    @PostMapping(value = "CallExpression_NoSourceCode_Native_034_T")
    public Map<String, Object> testcase(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            byte[] b1 = cmd.getBytes();
            ByteArrayOutputStream b2 = new ByteArrayOutputStream();
            b2.write(b1, 0, b1.length);
            Runtime.getRuntime().exec(b2.toString());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
