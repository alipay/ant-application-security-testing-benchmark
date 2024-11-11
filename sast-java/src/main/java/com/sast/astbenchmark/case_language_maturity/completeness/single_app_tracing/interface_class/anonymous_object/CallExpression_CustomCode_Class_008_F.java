package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.interface_class.anonymous_object;

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
 * Level 2
 * Date 2024-08-16
 */

// evaluation information start
// real case = false
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->特殊场景->自定义函数调用->匿名类
// bind_url = completeness/single_app_tracing/interface_class/anonymous_object/CallExpression_CustomCode_Class_008_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/interface_class/anonymous_object")
public class CallExpression_CustomCode_Class_008_F {
    @GetMapping("CallExpression_CustomCode_Class_008_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            ABS ac = new ABS() {
                @Override
                public String getCmd(){
                    return this.cmd;
                }
            };
            ac.setCmd("ls");
            Runtime.getRuntime().exec(ac.getCmd());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}

