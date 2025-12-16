package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.argument_passing;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 参数传递->多个参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/Argument_Passing_003_T
// date = 2025-12-09 14:12:00
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/argument_passing")
public class Argument_Passing_003_T {
  @GetMapping("Argument_Passing_003_T/{cmd}")
  public Map<String, Object> argument_passing_003_t(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    // 场景特点：多个参数拼接后传递给方法调用
    String result = testFunc(cmd, "param2");
    try {
      Runtime.getRuntime().exec(result);
      modelMap.put("status", "success");
    } catch (IOException e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }

  public String testFunc(String param1, String param2) {
    return param1 + param2;
  }
}