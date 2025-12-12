package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.arrow_function;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->箭头函数
// scene introduction = 箭头函数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/arrow_function/Arrow_Function_001_T
// date = 2025-12-09 14:12:00
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/arrow_function")
public class Arrow_Function_001_T {
  @GetMapping("Arrow_Function_001_T/{cmd}")
  public Map<String, Object> arrow_function_001_t(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    Function<String, String> function = (String s) -> {
      return s;
    };
    Runtime.getRuntime().exec(function.apply(cmd));
    modelMap.put("status", "success");
    return modelMap;
  }
}
