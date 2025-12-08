package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.primitives;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = boolean
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/Base_Boolean_001_T
// date = 2025-12-05 17:22:10
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/primitives")
public class Base_Boolean_001_T {
  @GetMapping("Base_Boolean_001_T/{cmd}")
  public Map<String, Object> base_boolean_001_t(@PathVariable boolean cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    // 场景特点：基本布尔类型变量传递
    SinkUtil.sink(cmd);
    modelMap.put("status", "success");
    return modelMap;
  }
}