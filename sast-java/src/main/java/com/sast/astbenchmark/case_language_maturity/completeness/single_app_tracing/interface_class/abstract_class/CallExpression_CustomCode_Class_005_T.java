package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.interface_class.abstract_class;

import com.sast.astbenchmark.model.custom.ABS;
import com.sast.astbenchmark.model.custom.AC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->抽象类的实现类
// scene introduction = 抽象类
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/abstract_class/CallExpression_CustomCode_Class_005_T
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/interface_class/abstract_class")
public class CallExpression_CustomCode_Class_005_T {
    @GetMapping("CallExpression_CustomCode_Class_005_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            ABS ac;
            ac = new AC();
            ac.setCmd(cmd);
            Runtime.getRuntime().exec(ac.getCmd());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
