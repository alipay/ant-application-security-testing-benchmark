package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.normal_stmt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->流敏感->常规顺序执行语句->数据流不可达->被固定值赋值
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感->常规顺序执行语句
// bind_url = accuracy/flow_sensitive/normal_stmt/AssignedByFixedValue_001_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/flow_sensitive/normal_stmt")
public class AssignedByFixedValue_001_F {
    @PostMapping(value = "AssignedByFixedValue_001_F")
    public Map<String, Object> aTaintCase0158(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd= "ls";
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
