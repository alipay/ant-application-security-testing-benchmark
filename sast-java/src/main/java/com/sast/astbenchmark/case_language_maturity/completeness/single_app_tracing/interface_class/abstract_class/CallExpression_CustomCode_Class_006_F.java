package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.interface_class.abstract_class;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sast.astbenchmark.model.custom.ABS;
import com.sast.astbenchmark.model.custom.AC;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->接口与类->抽象类的实现类->抽象类
 * Level 2
 * Date 2024-08-16
 */

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->抽象类的实现类
// bind_url = completeness/single_app_tracing/interface_class/abstract_class/CallExpression_CustomCode_Class_006_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/interface_class/abstract_class")
public class CallExpression_CustomCode_Class_006_F {
    @GetMapping("CallExpression_CustomCode_Class_006_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            ABS ac;
            ac = new AC();
            ac.setCmd("ls");
            Runtime.getRuntime().exec(ac.getCmd());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
