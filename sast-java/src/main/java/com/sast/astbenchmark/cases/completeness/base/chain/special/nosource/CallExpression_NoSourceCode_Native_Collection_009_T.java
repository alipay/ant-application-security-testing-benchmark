package com.sast.astbenchmark.cases.completeness.base.chain.special.nosource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的特殊场景-无源码函数-map.put
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->无源码函数调用->map.put
// compose = CallExpression_NoSourceCode_Native_Collection_009_T.java && !CallExpression_NoSourceCode_Native_Collection_010_F.java
// bind_url = completeness/base/chain/special/nosource/CallExpression_NoSourceCode_Native_Collection_009_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/special/nosource")
public class CallExpression_NoSourceCode_Native_Collection_009_T {
    @PostMapping("CallExpression_NoSourceCode_Native_Collection_009_T")
    public Map<String, Object> testcase(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("key", cmd);
            Runtime.getRuntime().exec(paramMap.get("key"));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
