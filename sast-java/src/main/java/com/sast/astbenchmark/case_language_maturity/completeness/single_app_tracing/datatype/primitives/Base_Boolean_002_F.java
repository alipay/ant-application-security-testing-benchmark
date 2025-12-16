package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.primitives;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = boolean
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/Base_Boolean_002_F
// date = 2025-12-05 17:22:10
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/primitives")
public class Base_Boolean_002_F {
  @GetMapping("Base_Boolean_002_F/{cmd}")
  public Map<String, Object> base_boolean_002_f(@PathVariable boolean cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    // 场景特点：基本布尔类型变量传递但使用固定值
    boolean b = false;
    try {
      Runtime.getRuntime().exec(String.valueOf(b));
      modelMap.put("status", "success");
    } catch (IOException e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}