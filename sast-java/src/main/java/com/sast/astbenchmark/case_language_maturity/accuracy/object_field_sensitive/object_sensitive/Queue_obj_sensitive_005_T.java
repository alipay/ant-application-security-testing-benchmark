package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.object_sensitive;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同的类对象、结构体/联合体和字典/列表/数组
// scene introduction = Queue-clear
// level = 2
// bind_url = accuracy/object_field_sensitive/object_sensitive/Queue_obj_sensitive_005_T
// date = 2025-12-10 18:45:00
// evaluation information end

@RestController
@RequestMapping("accuracy/object_field_sensitive/object_sensitive")
public class Queue_obj_sensitive_005_T {
  @GetMapping("Queue_obj_sensitive_005_T/{cmd}")
  public Map<String, Object> queue_obj_sensitive_005_t(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：Queue清空操作，但清空前已获取污点数据
      Queue<String> queue = new LinkedList<>();
      queue.offer(cmd);
      queue.clear();
      Runtime.getRuntime().exec(queue);
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}