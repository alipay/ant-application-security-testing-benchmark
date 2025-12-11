package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.variable_scope.public_variable;

import com.sast.astbenchmark.common.utils.SinkUtil;
import com.sast.astbenchmark.model.custom.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->变量作用域->public变量
// scene introduction = public变量
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/public_variable/Public_Variable_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/variable_scope/public_variable")
public class Public_Variable_002_F {
  @PostMapping(value = "public_variable_002_F")
  public Map<String, Object> public_variable_002_f(@RequestBody String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    P p = new P();
    p.str = "safe_value";
    try {
      SinkUtil.sink(p.str);
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}