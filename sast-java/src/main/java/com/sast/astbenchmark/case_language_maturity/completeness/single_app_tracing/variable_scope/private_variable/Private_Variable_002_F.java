package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.variable_scope.private_variable;

import com.sast.astbenchmark.common.utils.SinkUtil;
import com.sast.astbenchmark.model.custom.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->变量作用域->静态变量
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/private_variable/Private_Variable_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/variable_scope/private_variable")
public class Private_Variable_002_F {
  @PostMapping(value = "private_variable_002_F")
  public Map<String, Object> private_variable_002_f(@RequestBody String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    P p = new P("safe_value");
    try {
      SinkUtil.sink(p.getCmd());
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}