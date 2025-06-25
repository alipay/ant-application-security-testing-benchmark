package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.expression.basic_expression_operation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 中缀表达式+对象创建
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/Expression_ClassInstance_Infix_002_F
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/expression/basic_expression_operation")
public class Expression_ClassInstance_Infix_002_F {
    @PostMapping(value = "Expression_ClassInstance_Infix_002_F")
    public Map<String, Object> aTaintCase0113(@RequestParam(defaultValue = "ls") String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String result = new String(cmd + " &");
            result = new String("clean");
            Runtime.getRuntime().exec(result);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
