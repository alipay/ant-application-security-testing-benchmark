package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.object_sensitive;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同的类对象、结构体/联合体和字典/列表/数组
// scene introduction = Queue-offer
// level = 2
// bind_url = accuracy/object_field_sensitive/object_sensitive/Queue_obj_sensitive_002_F
// date = 2025-12-10 18:25:00
// evaluation information end

@RestController
@RequestMapping("accuracy/object_field_sensitive/object_sensitive")
public class Queue_obj_sensitive_002_F {
  @GetMapping("Queue_obj_sensitive_002_F/{cmd}")
  public Map<String, Object> queue_obj_sensitive_002_f(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：Queue入队操作，但使用固定安全值，输入与输出不一致
      Queue<String> queue = new LinkedList<>();
      queue.offer("safe_value");
      SinkUtil.sink(queue);
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}