package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.loop_conditional_stmt.solver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->能够对上下文条件进行求解，以区分不同执行路径的状态
// scene introduction = instanceof表达式-null
// level = 3
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/solver/Expression_InstanceofExpression_003_T/{cmd}
// evaluation information end
@RestController()
@RequestMapping("accuracy/path_sensitive/loop_conditional_stmt/solver")
public class Expression_InstanceofExpression_003_T {
    @GetMapping("Expression_InstanceofExpression_003_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            if (null instanceof String) {
                modelMap.put("status", "success");
            } else {
                Runtime.getRuntime().exec(cmd);
                modelMap.put("status", "error");
            }
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
