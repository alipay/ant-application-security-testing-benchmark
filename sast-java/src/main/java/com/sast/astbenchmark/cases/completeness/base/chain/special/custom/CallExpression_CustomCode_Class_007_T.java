package com.sast.astbenchmark.cases.completeness.base.chain.special.custom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sast.astbenchmark.model.custom.ABS;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的特殊场景-自定义函数-匿名类
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->特殊场景->自定义函数调用->匿名类
// compose = CallExpression_CustomCode_Class_007_T.java && !CallExpression_CustomCode_Class_008_F.java
// bind_url = completeness/base/chain/special/custom/CallExpression_CustomCode_Class_007_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/special/custom")
public class CallExpression_CustomCode_Class_007_T {
    @GetMapping("CallExpression_CustomCode_Class_007_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            ABS ac = new ABS() {
                @Override
                public String getCmd(){
                    return this.cmd;
                }
            };
            ac.setCmd(cmd);
            Runtime.getRuntime().exec(ac.getCmd());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}

