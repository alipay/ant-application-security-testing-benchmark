package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.collections;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合
// scene introduction = Set-remove
// level = 2
// bind_url = completeness/single_app_tracing/datatype/collections/Base_Set_006_F
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/collections")
public class Base_Set_006_F {
    @PostMapping("Base_Set_006_F")
    public Map<String, Object> aTaintCase0143(@RequestBody String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        Set<String> stringSet = new HashSet<>();
        stringSet.add(cmd);
        stringSet.add("_");
        stringSet.remove(cmd);
        try {
            Runtime.getRuntime().exec(stringSet.stream().iterator().next());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
