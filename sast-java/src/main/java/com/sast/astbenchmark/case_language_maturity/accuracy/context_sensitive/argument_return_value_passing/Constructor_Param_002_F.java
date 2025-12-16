package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.argument_return_value_passing;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 构造器参数
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/Constructor_Param_002_F
// date = 2025-12-10 15:15:00
// evaluation information end
@RestController()
@RequestMapping("accuracy/context_sensitive/argument_return_value_passing")
public class Constructor_Param_002_F {
  @GetMapping("Constructor_Param_002_F/{cmd}")
  public Map<String, Object> constructor_param_002_f(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：构造器参数传递，非污点参数通过构造器传递给对象
      CommandExecutor executor = new CommandExecutor("safe_value");
      Runtime.getRuntime().exec(executor.command);
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }

  static class CommandExecutor {
    private String command;

    public CommandExecutor(String command) {
      this.command = command;
    }

    public String getCommand() {
      return command;
    }
  }
}