package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.interface_class.interface_implementation;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->接口的实现
// scene introduction = 接口实现创建->类实现
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/interface_implementation/Interface_002_F
// date = 2025-12-08 16:31:16
// evaluation information end

@RestController
@RequestMapping("completeness/single_app_tracing/interface_class/interface_implementation")
public class Interface_002_F {

  interface CommandExecutor {
    String getCommand();
  }

  static class SimpleExecutor implements CommandExecutor {
    private String command;

    public SimpleExecutor(String command) {
      this.command = command;
    }

    @Override
    public String getCommand() {
      return command;
    }
  }

  @GetMapping("Interface_002_F/{cmd}")
  public Map<String, Object> interface_002_f(@RequestBody String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    // 场景特点：创建接口实现类实例但使用固定值，输入与输出不一致
    CommandExecutor executor = new SimpleExecutor("safe_value");
    try {
      Runtime.getRuntime().exec(executor.getCommand());
      modelMap.put("status", "success");
    } catch (IOException e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}