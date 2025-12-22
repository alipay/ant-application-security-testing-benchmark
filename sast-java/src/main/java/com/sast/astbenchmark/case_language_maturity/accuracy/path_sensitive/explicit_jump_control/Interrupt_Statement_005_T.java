package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.explicit_jump_control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感分析->跳转语句
// scene introduction = 中断语句->continue
// level = 4+
// bind_url = accuracy/path_sensitive/explicit_jump_control/Interrupt_Statement_005_T
// evaluation information end
@RestController()
@RequestMapping("accuracy/path_sensitive/explicit_jump_control")
public class Interrupt_Statement_005_T {
  @GetMapping("Interrupt_Statement_005_T/{cmd}")
  public Map<String, Object> interrupt_statement_005_t(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      String a = "mkdir";
      for (int i = 0; i < 10; i++) {
        a = cmd + "|";
        if (i == 5) {
          a = "ls";
          continue;
        }
      }
      Runtime.getRuntime().exec(a);
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}
