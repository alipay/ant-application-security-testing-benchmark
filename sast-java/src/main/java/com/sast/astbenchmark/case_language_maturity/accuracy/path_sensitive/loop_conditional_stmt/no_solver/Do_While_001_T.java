package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.loop_conditional_stmt.no_solver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
// scene introduction = 循环结构->do-while
// level = 3
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/Do_While_001_T
// date = 2025-12-11 11:02:45
// evaluation information end
@RestController
@RequestMapping("accuracy/path_sensitive/loop_conditional_stmt/no_solver")
public class Do_While_001_T {
  @GetMapping("Do_While_001_T/{cmd}")
  public Map<String, Object> do_while_001_t(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      String result;
      // 场景特点：do-while循环条件为常量true，循环体至少执行一次
      do {
        result = cmd;
        break;
      } while (true);
      Runtime.getRuntime().exec(result);
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}