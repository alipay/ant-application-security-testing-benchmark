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
// scene introduction = Map删除
// level = 2
// bind_url = completeness/single_app_tracing/datatype/map/Map_Delete_Single_001_T
// date = 2025-12-08 11:11:26
// evaluation information end

@RestController
@RequestMapping("completeness/single_app_tracing/datatype/map")
public class Map_Delete_Single_001_T {
  @GetMapping("Map_Delete_Single_001_T")
  public Map<String, Object> map_delete_single_001_t(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    Map<String, String> map = new HashMap<>();
    map.put("key", cmd);
    map.put("key2", "safe_value");
    map.remove("key2");
    try {
      Runtime.getRuntime().exec(map.toString());
      modelMap.put("status", "success");
    } catch (IOException e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}