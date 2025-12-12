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
// scene introduction = short
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/Base_Short_001_T
// date = 2025-12-05 17:22:10
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/primitives")
public class Base_Short_001_T {
  @GetMapping("Base_Short_001_T/{cmd}")
  public Map<String, Object> base_short_001_t(@PathVariable short cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    // 场景特点：基本短整型变量传递
    Runtime.getRuntime().exec(cmd);
    modelMap.put("status", "success");
    return modelMap;
  }
}