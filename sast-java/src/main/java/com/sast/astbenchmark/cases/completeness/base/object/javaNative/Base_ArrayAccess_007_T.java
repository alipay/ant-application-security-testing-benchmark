package com.sast.astbenchmark.cases.completeness.base.object.javaNative;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，多维数组中的对象为污点
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点对象完整度->java原生对象->多维数组
// compose = Base_ArrayAccess_007_T.java
// bind_url = completeness/base/object/javaNative/Base_ArrayAccess_007_T
// assession information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_ArrayAccess_007_T {
    @PostMapping(value = "Base_ArrayAccess_007_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[][] data = new String[1][1];
            data[0][0] = cmd;
            String[] slice = data[0];
            Runtime.getRuntime().exec(slice[0]);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
