package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.interface_class.simple_object;

import com.sast.astbenchmark.common.utils.SinkUtil;
import com.sast.astbenchmark.model.custom.O;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->基础跟踪能力->类/接口->简单类
 * Level 2
 * Date 2024-08-16
 */

// evaluation information start
// real case = false
// evaluation item = 完整度->基础跟踪能力->类/接口->简单类
// bind_url = completeness/single_app_tracing/interface_class/simple_object/simple_object_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/interface_class/simple_object")
public class simple_object_002_F {
    @PostMapping(value = "simple_object_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        O a = new O(cmd);
        O b = new O("_");
        try {
            SinkUtil.sink(b);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}