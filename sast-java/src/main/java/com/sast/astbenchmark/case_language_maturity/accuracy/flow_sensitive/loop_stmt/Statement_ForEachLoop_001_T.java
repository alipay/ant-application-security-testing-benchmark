package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.loop_stmt;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 准确度->流敏感分析->循环顺序执行语句
// scene introduction = for-each循环
// level = 2
// bind_url = accuracy/flow_sensitive/loop_stmt/Statement_ForEachLoop_001_T
// date = 2025-12-10 14:15:00
// evaluation information end
@RestController()
@RequestMapping("accuracy/flow_sensitive/loop_stmt")
public class Statement_ForEachLoop_001_T {
  @PostMapping("Statement_ForEachLoop_001_T")
  public Map<String, Object> statement_foreachloop_001_t(@RequestBody String[] cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      List<String> commands = Arrays.asList(cmd);
      // 场景特点：污点变量在for-each循环中作为集合元素
      for (String command : commands) {
        Runtime.getRuntime().exec(command);
      }
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}