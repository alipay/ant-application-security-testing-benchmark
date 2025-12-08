package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.interface_class.subclass;

import com.sast.astbenchmark.common.utils.SinkUtil;
import com.sast.astbenchmark.model.custom.O;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
// scene introduction = 嵌套类
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/subclass/Subclass_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/interface_class/subclass")
public class Subclass_002_F {
  @PostMapping(value = "Subclass_002_F")
  public Map<String, Object> subclass_002_f(@RequestBody String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    O o = new O("safe_value");
    try {
      SinkUtil.sink(o.getCmd());
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}

