package com.sast.astbenchmark.cases.completeness.base.chain.special;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Introduction 污点链路样本中的特殊场景-无源码函数-原生 linklist.clone
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->无源码函数调用->linklist.clone
// compose = CallExpression_NoSourceCode_Native_037_T.java
// bind_url = completeness/base/chain/special/CallExpression_NoSourceCode_Native_037_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/special")
public class CallExpression_NoSourceCode_Native_Collection_002_T {
    @PostMapping(value = "CallExpression_NoSourceCode_Native_037_T")
    public Map<String, Object> testcase(@RequestParam(defaultValue = "ls") String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            LinkedList<String> list = new LinkedList<String>();
            list.add(cmd);
            LinkedList<String> target = (LinkedList<String>) list.clone();
            Runtime.getRuntime().exec(target.get(0));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
