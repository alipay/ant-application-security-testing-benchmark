package com.sast.astbenchmark.cases.completeness.base.chain.special.custom;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
  
import com.sast.astbenchmark.model.custom.O;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的特殊场景-自定义函数-嵌套类
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->自定义函数调用->嵌套类
// compose = CallExpression_CustomCode_Class_001_T.java && !CallExpression_CustomCode_Class_002_F.java
// bind_url = completeness/base/chain/special/custom/CallExpression_CustomCode_Class_002_F
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/special/custom")
public class CallExpression_CustomCode_Class_002_F {
    @PostMapping(value = "CallExpression_CustomCode_Class_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        O a = new O("ls");
        try {
            Runtime.getRuntime().exec(a.getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}

