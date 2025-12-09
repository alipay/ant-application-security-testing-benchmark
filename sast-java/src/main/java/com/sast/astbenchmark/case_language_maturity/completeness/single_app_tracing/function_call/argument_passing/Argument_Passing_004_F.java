package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.argument_passing;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 参数传递->多个参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/Argument_Passing_004_F
// date = 2025-12-09 14:12:00
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/argument_passing")
public class Argument_Passing_004_F {
  @GetMapping("Argument_Passing_004_F/{cmd}")
  public Map<String, Object> argument_passing_004_f(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    // 场景特点：多个参数拼接中第一个被安全值覆盖，输入与输出不一致
    String result = testFunc(cmd, "param2");
    SinkUtil.sink(result);
    modelMap.put("status", "success");
    return modelMap;
  }

  public String testFunc(String param1, String param2) {
    return "safe_value" + param2;
  }
}