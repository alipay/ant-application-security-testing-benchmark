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
// scene introduction = 方法重载
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/MethodOverloading_001_T
// date = 2025-12-10 15:15:00
// evaluation information end
@RestController()
@RequestMapping("accuracy/context_sensitive/argument_return_value_passing")
public class MethodOverloading_001_T {
  @PostMapping("MethodOverloading_001_T")
  public Map<String, Object> methodoverloading_001_t(@RequestParam String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：方法重载时参数类型匹配，污点参数传递给重载方法
      String result = process(cmd);
      Runtime.getRuntime().exec(result);
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }

  private static String process(String param) {
    return param;
  }

  @SuppressWarnings("unused")
  private static String process(int param) {
    return String.valueOf(param);
  }
}