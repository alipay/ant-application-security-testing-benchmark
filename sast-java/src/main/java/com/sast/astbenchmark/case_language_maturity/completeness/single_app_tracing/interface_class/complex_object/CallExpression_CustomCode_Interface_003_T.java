package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.interface_class.complex_object;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
// scene introduction = 多重接口类型
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/complex_object/CallExpression_CustomCode_Interface_003_T
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/interface_class/complex_object")
public class CallExpression_CustomCode_Interface_003_T {
    @PostMapping(value = "CallExpression_CustomCode_Interface_003_T")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Inter s = (Serializable & Inter) (a, b) -> a + b;
            Runtime.getRuntime().exec(s.getCmd("ls", cmd));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    private interface Inter {
        String getCmd(String cmd, String cmd2);
    }
}
   