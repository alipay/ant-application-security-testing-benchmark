package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.interface_class.subclass;

import com.sast.astbenchmark.common.utils.SinkUtil;
import com.sast.astbenchmark.model.custom.O;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->子类对象
// scene introduction = 嵌套类
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/subclass/Subclass_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/interface_class/subclass")
public class Subclass_001_T {
  @PostMapping(value = "Subclass_001_T")
  public Map<String, Object> subclass_001_t(@RequestBody String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    O o = new O(cmd);
    try {
      SinkUtil.sink(o.getCmd());
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}

