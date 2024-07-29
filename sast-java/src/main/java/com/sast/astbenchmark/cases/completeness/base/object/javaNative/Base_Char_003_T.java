package com.sast.astbenchmark.cases.completeness.base.object.javaNative;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，Char作为污点
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点对象完整度->java原生对象->char
// compose = Base_Char_001_T.java || Base_Char_003_T.java
// bind_url = completeness/base/object/javaNative/Base_Char_003_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_Char_003_T {
    @GetMapping("Base_Char_003_T/{cmd}")
    public Map<String, Object> aTaintCase0137(@PathVariable char cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", "success");
        }
        catch (Exception e){
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
