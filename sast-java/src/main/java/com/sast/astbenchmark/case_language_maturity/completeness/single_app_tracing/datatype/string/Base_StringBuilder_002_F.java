package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.string;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字符串
// scene introduction = StringBuilder
// level = 2
// bind_url = completeness/single_app_tracing/datatype/string/Base_StringBuilder_002_F
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/string")
public class Base_StringBuilder_002_F {
    @PostMapping("Base_StringBuilder_002_F")
    public Map<String, Object> aTaintCase0154(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String s = "_";
        StringBuilder data = new StringBuilder();
        data.append(s);
        try {
            Runtime.getRuntime().exec(data.toString());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
