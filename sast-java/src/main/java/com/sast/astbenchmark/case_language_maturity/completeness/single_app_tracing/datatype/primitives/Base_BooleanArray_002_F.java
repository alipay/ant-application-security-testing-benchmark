package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.primitives;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = boolean数组
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/Base_BooleanArray_002_F
// date = 2025-12-05 17:22:10
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/primitives")
public class Base_BooleanArray_002_F {
  @PostMapping("Base_BooleanArray_002_F")
  public Map<String, Object> base_booleanarray_002_f(@RequestBody boolean[] cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    if (cmd == null || cmd.length < 1) {
      modelMap.put("status", "error");
      return modelMap;
    }
    // 场景特点：boolean数组类型传递但使用固定值
    boolean[] arr = new boolean[] { true, false };
    try {
      Runtime.getRuntime().exec(Arrays.toString(arr));
      modelMap.put("status", "success");
    } catch (IOException e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}