package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.interface_class.anonymous_object;

import com.sast.astbenchmark.model.custom.ABS;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->匿名对象
// scene introduction = 匿名类
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/anonymous_object/CallExpression_CustomCode_anonymous_object_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/interface_class/anonymous_object")
public class CallExpression_CustomCode_anonymous_object_002_F {
    @GetMapping("CallExpression_CustomCode_anonymous_object_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            ABS ac = new ABS() {
                @Override
                public String getCmd() {
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

