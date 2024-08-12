package com.sast.astbenchmark.cases.completeness.base.object.javaNative;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，LinkedList作为污点
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点对象完整度->java原生对象->LinkedList
// compose = Base_Integer_003_T.java || Base_Integer_004_T.java
// bind_url = completeness/base/object/javaNative/Base_Integer_004_T
// assession information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_Integer_004_T {
    @PostMapping("Base_Integer_004_T")
    public Map<String, Object> testcase(@RequestBody int cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
