package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.interface_class.complex_object;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->接口与类->复杂对象->局部类
 * Level 2
 * Date 2024-08-16
 */

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象->局部类
// bind_url = completeness/single_app_tracing/interface_class/complex_object/CallExpression_CustomCode_Class_004_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/interface_class/complex_object")
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