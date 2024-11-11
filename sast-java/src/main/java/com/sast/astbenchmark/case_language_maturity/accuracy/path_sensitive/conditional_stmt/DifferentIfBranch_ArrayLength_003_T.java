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
// real case = true
// evaluation project = 准确度->路径敏感->涉及求解问题->数组长度计算
// bind_url = accuracy/path_sensitive/conditional_stmt/DifferentIfBranch_ArrayLength_003_T
// evaluation information end
@RestController()
@RequestMapping("accuracy/path_sensitive/conditional_stmt")
public class DifferentIfBranch_ArrayLength_003_T {
    @PostMapping("DifferentIfBranch_ArrayLength_003_T")
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