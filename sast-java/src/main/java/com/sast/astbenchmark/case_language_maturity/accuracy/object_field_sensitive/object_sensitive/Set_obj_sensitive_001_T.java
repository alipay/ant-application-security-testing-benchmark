package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.object_sensitive;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同的类对象、结构体/联合体和字典/列表/数组
// scene introduction = Set-add
// level = 2
// bind_url = accuracy/object_field_sensitive/object_sensitive/Set_obj_sensitive_001_T
// date = 2025-12-10 18:25:00
// evaluation information end

@RestController
@RequestMapping("accuracy/object_field_sensitive/object_sensitive")
public class Set_obj_sensitive_001_T {
  @GetMapping("Set_obj_sensitive_001_T/{cmd}")
  public Map<String, Object> set_obj_sensitive_001_t(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：Set添加操作，污点数据通过add方法传播
      Set<String> set = new HashSet<>();
      set.add(cmd);
      // 由于Set无序，这里简化处理，实际场景中需要迭代器
      SinkUtil.sink(set.toArray());
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}