package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.expression.type_cast;

import com.sast.astbenchmark.common.utils.SinkUtil;
import com.sast.astbenchmark.model.custom.ABS;
import com.sast.astbenchmark.model.custom.AC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->类型转换
// scene introduction = 向上转型-父类引用
// level = 2
// bind_url = completeness/single_app_tracing/expression/type_cast/Expression_CastExpression_008_F
// date = 2025-12-09 11:31:00
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/expression/type_cast")
public class Expression_CastExpression_008_F {

  @GetMapping("Expression_CastExpression_008_F/{cmd}")
  public Map<String, Object> expression_castexpression_008_f(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：子类对象向上转型为父类引用但输入输出不一致
      AC abs = new AC();
      abs.setCmd(cmd);
      Runtime.getRuntime().exec(abs.getCmd());
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}