package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.interface_class.complex_object;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
  
import com.sast.astbenchmark.model.custom.O;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的特殊场景-自定义函数-嵌套类
 * Level 2
 * Date 2024-08-16
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->特殊场景->自定义函数调用->嵌套类
// bind_url = completeness/single_app_tracing/interface_class/complex_object/CallExpression_CustomCode_Class_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/interface_class/complex_object")
public class CallExpression_CustomCode_Class_001_T {
    @PostMapping(value = "CallExpression_CustomCode_Class_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        O a = new O(cmd);
        try {
            Runtime.getRuntime().exec(a.getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}

