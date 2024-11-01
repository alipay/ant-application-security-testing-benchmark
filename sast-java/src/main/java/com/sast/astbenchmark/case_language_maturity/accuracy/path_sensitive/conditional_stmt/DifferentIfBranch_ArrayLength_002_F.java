package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.conditional_stmt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 路径敏感-数组长度计算
 * Level 4
 * Date 2024-06-28
 */
// assession information start
// real vulnerability = false
// assession project = 准确度->路径敏感->涉及求解问题->数组长度计算
// compose = DifferentIfBranch_ArrayLength_001_T.java && !DifferentIfBranch_ArrayLength_002_F && DifferentIfBranch_ArrayLength_003_T.java
// bind_url = accuracy/pathSensitive/calculateValue/DifferentIfBranch_ArrayLength_002_F
// assession information end
@RestController()
@RequestMapping("accuracy/pathSensitive/calculateValue")
public class DifferentIfBranch_ArrayLength_002_F {
    @PostMapping("DifferentIfBranch_ArrayLength_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] arr = new String[]{"foo", "xx", "bar"};
            if (arr.length > 3) { // always false
                arr[1] = cmd; // UnReachable
            }
            Runtime.getRuntime().exec(arr);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}