package com.sast.astbenchmark.cases.completeness.base.object.javaNative;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，String对象为污点
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点对象完整度->java原生对象->String
// compose = Base_String_001_T.java
// bind_url = completeness/base/object/javaNative/Base_String_001_T
// assession information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_String_001_T {
    @PostMapping("Base_String_001_T")
    public Map<String, Object> aTaintCase0152(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
