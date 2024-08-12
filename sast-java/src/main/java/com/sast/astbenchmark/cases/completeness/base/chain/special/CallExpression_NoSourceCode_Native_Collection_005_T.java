package com.sast.astbenchmark.cases.completeness.base.chain.special;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，TreeMap作为污点
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点对象完整度->java原生对象->TreeMap
// compose = CallExpression_NoSourceCode_Native_Collection_005_T.java
// bind_url = completeness/base/chain/special/CallExpression_NoSourceCode_Native_Collection_005_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/special")
public class CallExpression_NoSourceCode_Native_Collection_005_T {
    @PostMapping("CallExpression_NoSourceCode_Native_Collection_005_T")
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
