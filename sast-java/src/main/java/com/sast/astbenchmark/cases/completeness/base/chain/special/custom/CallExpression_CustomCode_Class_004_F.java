package com.sast.astbenchmark.cases.completeness.base.chain.special.custom;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的特殊场景-自定义函数-局部类
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->自定义函数调用->局部类
// compose = CallExpression_CustomCode_Class_003_T.java && !CallExpression_CustomCode_Class_004_F.java
// bind_url = completeness/base/chain/special/custom/CallExpression_CustomCode_Class_004_F
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/special/custom")
public class CallExpression_CustomCode_Class_004_F {
    @PostMapping(value = "CallExpression_CustomCode_Class_004_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(local("ls"));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    public static String local(String cmd){
        class LocalClass {
            String getCmd(){
                return cmd;
            }
        }
        LocalClass local = new LocalClass();
        return local.getCmd();
    }

}