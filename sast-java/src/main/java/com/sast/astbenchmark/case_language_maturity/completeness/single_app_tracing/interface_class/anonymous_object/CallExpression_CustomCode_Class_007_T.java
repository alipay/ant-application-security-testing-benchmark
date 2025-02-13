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
 * Introduction 完整度->单应用跟踪完整度->接口与类->匿名对象->匿名类
 * Level 2
 * Date 2024-08-16
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->匿名对象->匿名类
// bind_url = completeness/single_app_tracing/interface_class/anonymous_object/CallExpression_CustomCode_Class_007_T
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/interface_class/anonymous_object")
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

