package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.argument_return_value_passing;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 方法重载
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/Method_Overloading_002_F
// date = 2025-12-10 15:15:00
// evaluation information end
@RestController()
@RequestMapping("accuracy/context_sensitive/argument_return_value_passing")
public class Method_Overloading_002_F {
  @GetMapping("MethodOverloading_002_F/{cmd}")
  public Map<String, Object> method_overloading_002_f(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：方法重载时参数类型不匹配，非污点参数传递给重载方法
      String result = process(Integer.parseInt(cmd));
      Runtime.getRuntime().exec(result);
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }

  @SuppressWarnings("unused")
  private static String process(String param) {
    return param;
  }

  private static String process(int param) {
    return "safe_value";
  }
}