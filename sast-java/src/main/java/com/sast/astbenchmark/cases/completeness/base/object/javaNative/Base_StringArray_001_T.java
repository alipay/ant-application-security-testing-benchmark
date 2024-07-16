package com.sast.astbenchmark.cases.completeness.base.object.javaNative;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，String[]对象为污点
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点对象完整度->java原生对象->String[]
// compose = Base_StringArray_001_T.java
// bind_url = completeness/base/object/javaNative/Base_StringArray_001_T
// assession information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_StringArray_001_T {
    @PostMapping("Base_StringArray_001_T")
    public Map<String, Object> aTaintCase0149(@RequestBody String[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null || cmd.length < 1) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            Runtime.getRuntime().exec(cmd[0]);
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
