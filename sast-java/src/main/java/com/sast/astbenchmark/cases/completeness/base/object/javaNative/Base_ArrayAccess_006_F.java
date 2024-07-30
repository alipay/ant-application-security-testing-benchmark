package com.sast.astbenchmark.cases.completeness.base.object.javaNative;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，Array中的对象为污点
 * Level X
 * Date 2024-06-28
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->基础跟踪能力->污点对象完整度->java原生对象->ArrayAccess-element assignment
// compose = Base_ArrayAccess_005_T.java && !Base_ArrayAccess_006_F.java
// bind_url = completeness/base/object/javaNative/Base_ArrayAccess_006_F
// assession information end
@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_ArrayAccess_006_F {
    @PostMapping("Base_ArrayAccess_006_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] arr = new String[]{"foo", "xx", "bar"};
            arr[1] = cmd;
            arr[1] = "foo";
            Runtime.getRuntime().exec(arr);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}