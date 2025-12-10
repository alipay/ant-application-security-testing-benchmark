package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.cross_file_package_namespace.cross_module;

import com.demo.SubModuleUtils;
import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
// scene introduction = 跨模块调用
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/Cross_Module_001_T
// date = 2025-12-10 10:31:20
// evaluation information end

@RestController
@RequestMapping("completeness/single_app_tracing/cross_file_package_namespace/cross_module")
public class Cross_Module_001_T {

  @GetMapping("Cross_Module_001_T/{cmd}")
  // 场景特点：跨模块方法调用，参数从控制器传递到模型类的处理方法
  public Map<String, Object> cross_module_001_t(@PathVariable String cmd) {

    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 跨模块调用，保持参数一致性
      String result = SubModuleUtils.getValue(cmd);
      SinkUtil.sink(result);
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}