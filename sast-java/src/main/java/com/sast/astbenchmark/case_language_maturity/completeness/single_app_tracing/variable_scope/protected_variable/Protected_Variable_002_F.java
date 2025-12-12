package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.variable_scope.protected_variable;

import com.sast.astbenchmark.common.utils.SinkUtil;
import com.sast.astbenchmark.model.custom.AC;
import com.sast.astbenchmark.model.custom.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->变量作用域->protected变量
// scene introduction = protected变量
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/protected_variable/Protected_Variable_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/variable_scope/protected_variable")
public class Protected_Variable_002_F {
  @PostMapping(value = "protected_variable_002_F")
  public Map<String, Object> protected_variable_002_f(@RequestBody String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    AC ac = new AC();
    ac.setCmd("safe_value");
    try {
      Runtime.getRuntime().exec(ac.getCmd());
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}