package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.interface_class.interface_implementation;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->接口的实现
// scene introduction = 接口实现创建->类实现
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/interface_implementation/Interface_001_T
// date = 2025-12-08 16:31:16
// evaluation information end

@RestController
@RequestMapping("completeness/single_app_tracing/interface_class/interface_implementation")
public class Interface_001_T {

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

  @PostMapping("Interface_001_T")
  public Map<String, Object> interface_001_t(@RequestBody Map<String, String> cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    // 场景特点：创建接口实现类实例并传递污点数据
    CommandExecutor executor = new SimpleExecutor(cmd.get("data"));
    Runtime.getRuntime().exec(executor.getCommand());
    modelMap.put("status", "success");
    return modelMap;
  }
}