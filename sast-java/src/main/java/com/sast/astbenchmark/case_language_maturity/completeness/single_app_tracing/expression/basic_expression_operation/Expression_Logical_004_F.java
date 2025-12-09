package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.expression.basic_expression_operation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import com.sast.astbenchmark.common.utils.SinkUtil;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 逻辑运算->或运算
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/Expression_Logical_004_F
// date = 2025-12-08 18:45:00
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/expression/basic_expression_operation")
public class Expression_Logical_004_F {
  @GetMapping("Expression_Logical_004_F/{cmd}")
  public Map<String, Object> expression_logical_004_f(@PathVariable boolean cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：或运算后结果被固定值覆盖，输入与输出不一致
      boolean result = cmd || false;
      result = true;
      SinkUtil.sink(result);
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}