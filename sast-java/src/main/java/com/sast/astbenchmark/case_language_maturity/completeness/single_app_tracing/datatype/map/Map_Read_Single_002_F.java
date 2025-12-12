package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.map;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
// scene introduction = Map读取
// level = 2
// bind_url = completeness/single_app_tracing/datatype/map/Map_Read_Single_002_F
// date = 2025-12-08 11:11:26
// evaluation information end

@RestController
@RequestMapping("completeness/single_app_tracing/datatype/map")
public class Map_Read_Single_002_F {
  @PostMapping("Map_Read_Single_002_F")
  public Map<String, Object> map_read_single_002_f(@RequestBody String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    if (cmd == null) {
      modelMap.put("status", "error");
      return modelMap;
    }
    Map<String, String> map = new HashMap<>();
    map.put("input", "safe_value");
    // 场景特点：读取固定值而非Map中的值，输入与输出不一致
    String value = map.get("key");
    Runtime.getRuntime().exec(value);
    modelMap.put("status", "success");
    return modelMap;
  }
}