package com.sast.astbenchmark.cases.completeness.base.chain.special;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的特殊场景-非常规类函数-局部类
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->非常规类函数->局部类
// compose = CallExpression_CustomCode_Class_002_T.java
// bind_url = completeness/base/chain/special/CallExpression_CustomCode_Class_002_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/special")
public class CallExpression_CustomCode_Class_002_T {
    @PostMapping(value = "CallExpression_CustomCode_Class_002_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(local(cmd));
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