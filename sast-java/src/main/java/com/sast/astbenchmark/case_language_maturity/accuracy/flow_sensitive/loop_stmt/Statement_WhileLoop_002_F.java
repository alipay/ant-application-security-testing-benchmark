package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.loop_stmt;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感分析->循环顺序执行语句
// scene introduction = while循环体
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/Statement_WhileLoop_002_F
// date = 2025-12-10 14:15:00
// evaluation information end
@RestController()
@RequestMapping("accuracy/flow_sensitive/loop_stmt")
public class Statement_WhileLoop_002_F {
  @GetMapping("Statement_WhileLoop_002_F/{cmd}")
  public Map<String, Object> statement_whileloop_002_f(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      int i = 0;
      // 场景特点：非污点变量在while循环中使用
      while (i < 1) {
        Runtime.getRuntime().exec("safe_value");
        i++;
      }
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}