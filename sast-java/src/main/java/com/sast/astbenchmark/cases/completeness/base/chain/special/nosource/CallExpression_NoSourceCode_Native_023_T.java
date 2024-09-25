package com.sast.astbenchmark.cases.completeness.base.chain.special.nosource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的特殊场景-无源码函数-stringBuilder.delete
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->无源码函数调用->stringBuilder.delete()
// compose = CallExpression_NoSourceCode_Native_023_T.java
// bind_url = completeness/base/chain/special/nosource/CallExpression_NoSourceCode_Native_023_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/special/nosource")
public class CallExpression_NoSourceCode_Native_023_T {
    @PostMapping(value = "CallExpression_NoSourceCode_Native_023_T")
    public Map<String, Object> aTaintCase0184(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(cmd);
            builder.delete(2,cmd.length());
            Runtime.getRuntime().exec(builder.toString());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
