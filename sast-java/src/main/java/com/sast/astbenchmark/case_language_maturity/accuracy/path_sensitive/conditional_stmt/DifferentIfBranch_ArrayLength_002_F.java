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
// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感->涉及求解问题->数组长度计算
// bind_url = accuracy/path_sensitive/conditional_stmt/DifferentIfBranch_ArrayLength_002_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/path_sensitive/conditional_stmt")
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