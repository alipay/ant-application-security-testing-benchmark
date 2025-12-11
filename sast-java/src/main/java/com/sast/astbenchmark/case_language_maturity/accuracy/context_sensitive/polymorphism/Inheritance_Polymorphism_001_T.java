package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.polymorphism;

import com.sast.astbenchmark.common.utils.SinkUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感分析->多态
// scene introduction = 子类重写父类方法
// level = 2
// bind_url = accuracy/context_sensitive/polymorphism/Inheritance_Polymorphism_001_T
// date = 2025-12-10 15:57:27
// evaluation information end

class Parent {
  public String getCommand(String input) {
    return input;
  }
}

class Child extends Parent {
  @Override
  public String getCommand(String input) {
    // 场景特点：子类重写父类方法，保持污点传播
    return super.getCommand(input);
  }
}

@RestController
@RequestMapping("accuracy/context_sensitive/polymorphism")
public class Inheritance_Polymorphism_001_T {
  @GetMapping("Inheritance_Polymorphism_001_T/{cmd}")
  public Map<String, Object> inheritance_polymorphism_001_t(@PathVariable String cmd) {
    Map<String, Object> modelMap = new HashMap<>();
    try {
      // 场景特点：父类引用指向子类对象的多态调用
      Parent processor = new Child();
      String command = processor.getCommand(cmd);
      SinkUtil.sink(command);
      modelMap.put("status", "success");
    } catch (Exception e) {
      modelMap.put("status", "error");
    }
    return modelMap;
  }
}