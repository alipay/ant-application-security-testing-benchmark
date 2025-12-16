package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.interface_class.simple_object;

import com.sast.astbenchmark.common.utils.SinkUtil;
import com.sast.astbenchmark.model.custom.O;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->接口与类->简单对象
// scene introduction = 简单类
// level = 2
// bind_url = completeness/single_app_tracing/interface_class/simple_object/simple_object_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/interface_class/simple_object")
public class simple_object_001_T {
    @PostMapping(value = "simple_object_001_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        O a = new O(cmd);
        try {
            Runtime.getRuntime().exec(String.valueOf(a));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}