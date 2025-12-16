package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.interface_class.interface_implementation;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->接口的实现
// scene introduction = 接口实现创建->匿名实现
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/interface_implementation/Interface_003_T
// date = 2025-12-08 16:31:16
// evaluation information end

@RestController
@RequestMapping("completeness/single_app_tracing/interface_class/interface_implementation")
public class Interface_003_T {

  interface CommandExecutor {
    String getCommand();
  }

  @GetMapping("Interface_003_T/{cmd}")
  public Map<String, Object> interface_003_t(@RequestBody String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    // 场景特点：创建接口的匿名实现并传递污点数据
    CommandExecutor executor = new CommandExecutor() {
      private String command = cmd;

      @Override
      public String getCommand() {
        return command;
      }
    };
    try {
      Runtime.getRuntime().exec(executor.getCommand());
      modelMap.put("status", "success");
    } catch (IOException e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}