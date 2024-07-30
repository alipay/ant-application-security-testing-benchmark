package com.sast.astbenchmark.cases.completeness.base.object.javaNative;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，数组长度影响污点传递
 * Level X
 * Date 2024-06-28
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点对象完整度->java原生对象->ArrayAccess-AccessWithArrayLength
// compose = Base_ArrayAccessWithArrayLength_001_T.java && !Base_ArrayAccessWithArrayLength_002_F && Base_ArrayAccessWithArrayLength_003_T.java
// bind_url = completeness/base/object/javaNative/Base_ArrayAccessWithArrayLength_003_T
// assession information end
@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_ArrayAccessWithArrayLength_003_T {
    @PostMapping("Base_ArrayAccessWithArrayLength_003_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] arr1 = new String[]{"foo", "xx", "bar"};
            String[] arr2 = new String[arr1.length + 1];
            if (arr2.length > 3) { // always true
                arr2[1] = cmd;
            }
            Runtime.getRuntime().exec(arr2);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}