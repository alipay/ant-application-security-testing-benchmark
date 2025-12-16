package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.collections;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合
// scene introduction = List-remove
// level = 2
// bind_url = completeness/single_app_tracing/datatype/collections/Base_List_006_F
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/collections")
public class Base_List_006_F {
  @PostMapping("Base_List_006_F")
  public Map<String, Object> testcase(@RequestBody String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    List<String> list = new ArrayList<>();
    list.add(cmd);
    list.add("_");
    list.remove(cmd);
    try {
      Runtime.getRuntime().exec(list.toString());
      modelMap.put("status", "success");
    } catch (IOException e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}
