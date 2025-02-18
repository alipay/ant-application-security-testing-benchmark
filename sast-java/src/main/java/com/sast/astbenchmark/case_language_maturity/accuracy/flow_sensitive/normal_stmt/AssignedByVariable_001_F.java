package com.sast.astbenchmark.case_language_maturity.accuracy.flow_sensitive.normal_stmt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->流敏感->常规顺序执行语句->数据流不可达->被固定值通过变量赋值
 * Level 2
 * Date 2024-08-16
 */
// evaluation information start
// real case = false
// evaluation item = 准确度->流敏感->常规顺序执行语句
// bind_url = accuracy/flow_sensitive/normal_stmt/AssignedByVariable_001_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/flow_sensitive/normal_stmt")
public class AssignedByVariable_001_F {
    @PostMapping(value = "AssignedByVariable_001_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a = cmd;
            String b = "ls";
            a = b;
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
