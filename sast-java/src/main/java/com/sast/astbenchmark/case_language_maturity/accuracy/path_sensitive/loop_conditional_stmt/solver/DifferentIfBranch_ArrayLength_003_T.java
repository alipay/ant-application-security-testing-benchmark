package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.loop_conditional_stmt.solver;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
// scene introduction = 数组长度计算
// level = 4
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/DifferentIfBranch_ArrayLength_003_T
// evaluation information end
@RestController()
@RequestMapping("accuracy/path_sensitive/loop_conditional_stmt/solver")
public class DifferentIfBranch_ArrayLength_003_T {
    @PostMapping("DifferentIfBranch_ArrayLength_003_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String[] arr1 = new String[] {"foo", "xx", "bar"};
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