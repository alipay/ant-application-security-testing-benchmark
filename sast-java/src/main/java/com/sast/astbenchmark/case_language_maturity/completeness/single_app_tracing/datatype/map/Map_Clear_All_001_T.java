package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.map;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
// scene introduction = Map清空
// level = 2
// bind_url = completeness/single_app_tracing/datatype/map/Map_Clear_All_001_T
// date = 2025-12-08 11:11:26
// evaluation information end

@RestController
@RequestMapping("completeness/single_app_tracing/datatype/map")
public class Map_Clear_All_001_T {
  @PostMapping("Map_Clear_All_001_T")
  public Map<String, Object> map_clear_all_001_t(@RequestBody String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    if (cmd == null) {
      modelMap.put("status", "error");
      return modelMap;
    }
    Map<String, String> map = new HashMap<>();
    map.put("key", cmd);
    // 场景特点：清空Map前将Map作为污点传递
    map.clear();
    try {
      Runtime.getRuntime().exec(map.toString());
      modelMap.put("status", "success");
    } catch (IOException e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}