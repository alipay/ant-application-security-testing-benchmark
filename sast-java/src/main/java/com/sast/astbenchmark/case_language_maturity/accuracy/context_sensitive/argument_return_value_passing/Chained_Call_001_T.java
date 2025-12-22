package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.argument_return_value_passing;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 链式调用
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/Chained_Call_001_T
// date = 2025-12-10 15:15:00
// evaluation information end

@RestController()
@RequestMapping("accuracy/context_sensitive/argument_return_value_passing")
public class Chained_Call_001_T {
  @GetMapping("Chained_Call_001_T/{cmd}")
  public Map<String, Object> chained_call_001_t(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：链式调用中的返回值传递，污点通过链式调用传递
      String result = new CommandProcessor().setCommand(cmd).process();
      Runtime.getRuntime().exec(result);
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }

  static class CommandProcessor {
    private String command;

    public CommandProcessor setCommand(String command) {
      this.command = command;
      return this;
    }

    public String process() {
      return command;
    }
  }
}