package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.multi_invoke;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感分析->多次调用
// scene introduction = 流式API->Stream操作
// level = 2
// bind_url = accuracy/context_sensitive/multi_invoke/StreamAPI_001_T
// date = 2025-12-10 15:40:00
// evaluation information end
@RestController
@RequestMapping("accuracy/context_sensitive/multi_invoke")
public class StreamAPI_001_T {
  @GetMapping("StreamAPI_001_T/{cmd}")
  public Map<String, Object> streamapi_001_t(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      List<String> commands = Arrays.asList(cmd);
      // 场景特点：流式API中的多次调用，污点通过Stream操作传递
      String result = commands.stream()
          .filter(s -> !s.isEmpty())
          .findFirst()
          .orElse("default");
      SinkUtil.sink(result);
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}