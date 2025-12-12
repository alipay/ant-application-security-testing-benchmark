package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.argument_return_value_passing;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 构造器参数
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/ConstructorParam_001_T
// date = 2025-12-10 15:15:00
// evaluation information end
@RestController()
@RequestMapping("accuracy/context_sensitive/argument_return_value_passing")
public class ConstructorParam_001_T {
  @PostMapping("ConstructorParam_001_T")
  public Map<String, Object> constructorparam_001_t(@RequestParam String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：构造器参数传递，污点参数通过构造器传递给对象
      CommandExecutor executor = new CommandExecutor(cmd);
      executor.execute();
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

    public void execute() throws Exception {
      Runtime.getRuntime().exec(command);
    }
  }
}