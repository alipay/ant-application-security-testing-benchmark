package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.cross_file_package_namespace.cross_module;

import com.demo.SubModuleUtils;
import com.sast.astbenchmark.common.utils.SinkUtil;
import com.sast.astbenchmark.model.custom.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
// scene introduction = 跨模块调用
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/Cross_Module_002_F
// date = 2025-12-10 10:31:20
// evaluation information end

@RestController
@RequestMapping("completeness/single_app_tracing/cross_file_package_namespace/cross_module")
public class Cross_Module_002_F {

  @GetMapping("Cross_Module_002_F/{cmd}")
  // 场景特点：跨模块方法调用，但参数在传递过程中被修改，导致输入输出不一致
  public Map<String, Object> cross_module_002_f(@PathVariable String cmd) {
    // 跨模块调用，但使用固定字符串，与输入参数不一致
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：使用跨模块的P类处理固定参数，与输入cmd不一致
      String result = SubModuleUtils.getValue("safe_value");
      SinkUtil.sink(result);
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}