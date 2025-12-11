package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.loop_conditional_stmt.no_solver;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->条件语句、条件表达式和循环结构->无需通过对不同的条件进行求解，即能够区分不同的执行路径的状态
// scene introduction = 循环结构->while
// level = 3
// bind_url = accuracy/path_sensitive/loop_conditional_stmt/no_solver/Statement_While_002_F
// date = 2025-12-11 11:02:30
// evaluation information end
@RestController
@RequestMapping("accuracy/path_sensitive/loop_conditional_stmt/no_solver")
public class Statement_While_002_F {
  @GetMapping("Statement_While_002_F/{cmd}")
  public Map<String, Object> statement_while_002_f(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      String result = "safe_value";
      Boolean flag = false;
      // 场景特点：while循环条件为常量false，循环体不执行
      while (flag) {
        result = cmd;
        break;
      }
      SinkUtil.sink(result);
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}