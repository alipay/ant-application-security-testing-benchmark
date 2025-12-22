package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.expression.basic_expression_operation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import com.sast.astbenchmark.common.utils.SinkUtil;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 比较运算->小于等于
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/Expression_Comparison_011_T
// date = 2025-12-08 18:49:00
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/expression/basic_expression_operation")
public class Expression_Comparison_011_T {
  @GetMapping("Expression_Comparison_011_T/{cmd}")
  public Map<String, Object> expression_comparison_011_t(@PathVariable int cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：小于等于比较运算保持污点传播
      int result = cmd;
      Runtime.getRuntime().exec(String.valueOf(result));
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}