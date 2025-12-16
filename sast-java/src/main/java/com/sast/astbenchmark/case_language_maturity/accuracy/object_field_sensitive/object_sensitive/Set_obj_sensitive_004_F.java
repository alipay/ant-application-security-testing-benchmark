package com.sast.astbenchmark.case_language_maturity.accuracy.object_field_sensitive.object_sensitive;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同的类对象、结构体/联合体和字典/列表/数组
// scene introduction = Set-remove
// level = 2
// bind_url = accuracy/object_field_sensitive/object_sensitive/Set_obj_sensitive_004_F
// date = 2025-12-10 18:25:00
// evaluation information end

@RestController
@RequestMapping("accuracy/object_field_sensitive/object_sensitive")
public class Set_obj_sensitive_004_F {
  @GetMapping("Set_obj_sensitive_004_F/{cmd}")
  public Map<String, Object> set_obj_sensitive_004_f(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：Set移除操作，先添加再移除其他元素，保留污点数据
      Set<String> set = new HashSet<>();
      set.add(cmd);
      set.add("safe_value");
      set.remove(cmd); // 移除安全值，保留污点
      Runtime.getRuntime().exec(set.toString());
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}