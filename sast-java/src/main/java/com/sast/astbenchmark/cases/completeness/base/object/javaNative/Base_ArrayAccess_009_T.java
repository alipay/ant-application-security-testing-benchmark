package com.sast.astbenchmark.cases.completeness.base.object.javaNative;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，Array中的对象为污点-数组元素净化
 * Level X
 * Date 2024-06-28
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点对象完整度->java原生对象->ArrayAccess-element sanitizer
// compose = Base_ArrayAccess_009_T.java && !Base_ArrayAccess_010_F.java
// bind_url = completeness/base/object/javaNative/Base_ArrayAccess_009_T
// assession information end
@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_ArrayAccess_009_T {
    @PostMapping("Base_ArrayAccess_009_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] arr = new String[]{"foo", cmd, "bar", "test"};
            if (Math.random() < 0.5) {
                arr[1] = arr[0];
            } else if (Math.random() < 0.5) {
                arr[1] = arr[2];
            }
            Runtime.getRuntime().exec(arr);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}