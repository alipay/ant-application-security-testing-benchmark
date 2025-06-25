package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.primitives;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = byte
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/Base_Byte_006_F/{cmd}
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/primitives")
public class Base_Byte_006_F {
    @GetMapping("Base_Byte_006_F/{cmd}")
    public Map<String, Object> aTaintCase0138_2(@PathVariable byte cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        byte b = 0;
        try {
            Runtime.getRuntime().exec(String.valueOf(b));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

}
