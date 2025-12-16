package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.expression.type_cast;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->类型转换
// scene introduction = 包装类型(装箱/拆箱)
// level = 2
// bind_url = completeness/single_app_tracing/expression/type_cast/Expression_CastExpression_011_T
// date = 2025-12-09 11:31:00
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/expression/type_cast")
public class Expression_CastExpression_011_T {

  @GetMapping("Expression_CastExpression_011_T/{cmd}")
  public Map<String, Object> expression_castexpression_011_t(@PathVariable int cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：int类型强制转换为double类型
      Integer integer = cmd;
      Runtime.getRuntime().exec(String.valueOf(integer));
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}