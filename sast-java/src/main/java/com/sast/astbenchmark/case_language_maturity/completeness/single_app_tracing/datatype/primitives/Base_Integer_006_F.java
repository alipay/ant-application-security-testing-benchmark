package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.primitives;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = int
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/Base_Integer_006_F
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/primitives")
public class Base_Integer_006_F {
  @PostMapping("Base_Integer_006_F/{cmd}")
  public Map<String, Object> testcase(@PathVariable int cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    int i = 0;
    try {
      Runtime.getRuntime().exec(String.valueOf(i));
      modelMap.put("status", "success");
    } catch (IOException e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}
