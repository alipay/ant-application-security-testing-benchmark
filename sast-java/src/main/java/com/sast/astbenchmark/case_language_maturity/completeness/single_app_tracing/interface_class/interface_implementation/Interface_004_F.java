package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.interface_class.interface_implementation;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->接口的实现
// scene introduction = 接口实现创建->匿名实现
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/interface_implementation/Interface_004_F
// date = 2025-12-08 16:31:16
// evaluation information end

@RestController
@RequestMapping("completeness/single_app_tracing/interface_class/interface_implementation")
public class Interface_004_F {

  interface CommandExecutor {
    String getCommand();
  }

  @GetMapping("Interface_004_F")
  public Map<String, Object> interface_004_f(@RequestBody String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    // 场景特点：创建接口的匿名实现但使用固定值，输入与输出不一致
    CommandExecutor executor = new CommandExecutor() {
      private String command = "safe_value";

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