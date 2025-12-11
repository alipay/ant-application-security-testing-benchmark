package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.loop_conditional_stmt.no_solver;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
// scene introduction = 三目运算符
// level = 3
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/Ternary_Operator_001_T
// date = 2025-12-11 11:03:00
// evaluation information end
@RestController
@RequestMapping("accuracy/path_sensitive/loop_conditional_stmt/no_solver")
public class Ternary_Operator_001_T {
  @GetMapping("Ternary_Operator_001_T/{cmd}")
  public Map<String, Object> ternary_operator_001_t(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：三目运算符条件为常量true，执行true分支
      String result = true ? cmd : "safe_value";
      SinkUtil.sink(result);
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}