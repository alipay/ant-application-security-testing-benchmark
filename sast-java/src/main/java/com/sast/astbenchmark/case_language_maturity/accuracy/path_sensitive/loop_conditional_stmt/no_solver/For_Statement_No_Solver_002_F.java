package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.loop_conditional_stmt.no_solver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
// scene introduction = 循环语句->for
// level = 3
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/For_Statement_No_Solver_002_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/path_sensitive/loop_conditional_stmt/no_solver")
public class For_Statement_No_Solver_002_F {
    @GetMapping("For_Statement_No_Solver_002_F/{cmd}")
    public Map<String, Object> for_statement_no_solver_002_f(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a = "mkdir";
            for (int i = 0; i < 0; i++) {
                a = cmd + "|";
            }
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
