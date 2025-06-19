package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字典
// scene introduction = Map
// level = 2
// bind_url = completeness/single_app_tracing/datatype/map/Base_Map_004_F
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/map")
public class Base_Map_004_F {
    @PostMapping("Base_Map_004_F")
    public Map<String, Object> aTaintCase0140(@RequestBody Map<String, String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        Map<String, String> map = new HashMap<>();
        map.put("key1", "_");
        try {
            Runtime.getRuntime().exec(String.valueOf(map));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
