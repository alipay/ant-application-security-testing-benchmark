package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.expression.basic_expression_operation;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 变量传递通过native方法发生在两个入参上
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/Expression_AssignmentExpression_002_F/{cmd}
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/expression/basic_expression_operation")
public class Expression_AssignmentExpression_002_F {
    @PostMapping("Expression_AssignmentExpression_002_F/{cmd}")
    public Map<String, Object> aTaintCase018(@PathVariable String[] cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        if (cmd == null) {
            modelMap.put("status", "error");
            return modelMap;
        }
        try {
            String[] b = {"a", "b"};
            System.arraycopy(cmd, 0, b, 0, 2);
            b = new String[] {"clean", "clean"};
            Runtime.getRuntime().exec(b[0]);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
