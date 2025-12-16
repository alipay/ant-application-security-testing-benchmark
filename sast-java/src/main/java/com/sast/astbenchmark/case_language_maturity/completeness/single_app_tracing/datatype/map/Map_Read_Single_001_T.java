package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.map;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
// scene introduction = Map读取
// level = 2
// bind_url = completeness/single_app_tracing/datatype/map/Map_Read_Single_001_T
// date = 2025-12-08 11:11:26
// evaluation information end

@RestController
@RequestMapping("completeness/single_app_tracing/datatype/map")
public class Map_Read_Single_001_T {
  @GetMapping("Map_Read_Single_001_T/{cmd}")
  public Map<String, Object> map_read_single_001_t(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    Map<String, String> map = new HashMap<>();
    map.put("key", cmd);
    map.put("key2", "safe_value");
    // 场景特点：从Map中读取单个键值并作为污点传递
    String value = map.get("key");
    try {
      Runtime.getRuntime().exec(value);
      modelMap.put("status", "success");
    } catch (IOException e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}