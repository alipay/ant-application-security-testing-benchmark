package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.higher_order_function;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
// scene introduction = 高阶函数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/higher_order_function/Higher_Order_Function_004_F
// date = 2025-12-09 14:12:00
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/higher_order_function")
public class Higher_Order_Function_004_F {
  @GetMapping("Higher_Order_Function_004_F/{cmd}")
  public Map<String, Object> higher_order_function_004_f(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    String result = testFn(str -> str).apply("safe_value").toString();
    SinkUtil.sink(result);
    modelMap.put("status", "success");
    return modelMap;
  }

  public <T, R> Function<T, R> testFn(Function<T, R> function) {
    return x -> function.apply(x);
  }
}
