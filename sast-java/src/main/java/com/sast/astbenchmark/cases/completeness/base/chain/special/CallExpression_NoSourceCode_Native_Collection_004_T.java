package com.sast.astbenchmark.cases.completeness.base.chain.special;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Introduction 对象中的简单类型对象，基于LinkedList的Queue对象为污点
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点对象完整度->java原生对象->基于LinkedList的Queue
// compose = Base_Queue_002_T.java
// bind_url = completeness/base/chain/special/Base_Queue_002_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/special")
public class CallExpression_NoSourceCode_Native_Collection_004_T {
    @PostMapping("Base_Queue_002_T")
    public Map<String, Object> aTaintCase0142(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Queue<String> queue = new LinkedList<>();
            queue.add(cmd);
            Runtime.getRuntime().exec(queue.peek());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
