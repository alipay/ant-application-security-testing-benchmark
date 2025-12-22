package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.primitives;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = short
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/Base_Short_003_T
// date = 2025-12-05 17:22:10
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/primitives")
public class Base_Short_003_T {
  @PostMapping("Base_Short_003_T")
  public Map<String, Object> base_short_003_t(@RequestBody Short cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    if (cmd == null) {
      modelMap.put("status", "error");
      return modelMap;
    }
    // 场景特点：Short包装类对象传递
    try {
      Runtime.getRuntime().exec(String.valueOf(cmd));
      modelMap.put("status", "success");
    } catch (IOException e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}