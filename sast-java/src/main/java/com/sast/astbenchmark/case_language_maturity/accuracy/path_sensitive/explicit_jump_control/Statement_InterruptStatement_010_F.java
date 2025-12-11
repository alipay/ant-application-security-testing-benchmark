package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.explicit_jump_control;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 准确度->路径敏感分析->跳转语句
// scene introduction = 跳转语句->return
// level = 4+
// bind_url = accuracy/path_sensitive/explicit_jump_control/Statement_InterruptStatement_010_F
// date = 2025-12-11 11:37:30
// evaluation information end
@RestController
@RequestMapping("accuracy/path_sensitive/explicit_jump_control")
public class Statement_InterruptStatement_010_F {
  @GetMapping("Statement_InterruptStatement_010_F/{cmd}")
  public Map<String, Object> statement_interruptstatement_010_f(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：return语句不执行，后续代码继续执行
      if (false) {
        SinkUtil.sink(cmd);
        modelMap.put("status", "success");
        return modelMap;
      }
      SinkUtil.sink("safe_value");
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}