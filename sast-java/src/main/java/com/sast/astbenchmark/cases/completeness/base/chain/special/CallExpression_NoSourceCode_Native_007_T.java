package com.sast.astbenchmark.cases.completeness.base.chain.special;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的特殊场景-无源码函数-string valueOf
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->无源码函数调用->string.getChars
// compose = CallExpression_NoSourceCode_Native_007_T.java
// bind_url = completeness/base/chain/special/CallExpression_NoSourceCode_Native_007_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/special")
public class CallExpression_NoSourceCode_Native_007_T {
    @PostMapping(value = "CallExpression_NoSourceCode_Native_007_T")
    public Map<String, Object> aTaintCase0165(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            char[] chars= new char[]{0,0};
            cmd.getChars(0,2,chars,0);
            Runtime.getRuntime().exec(String.valueOf(chars));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
