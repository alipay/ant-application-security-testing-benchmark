package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.collections;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

/**
 * Introduction 完整度->单应用跟踪完整度->数据类型和结构->集合->Set
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合->Set
// bind_url = completeness/base/object/javaNative/Base_Set_004_F
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_Set_004_F {
    @PostMapping("Base_Set_004_F")
    public Map<String, Object> aTaintCase0143(@RequestBody List<String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        List<String> list = Arrays.asList("a", "b", "c");
        Set<String> stringSet = new HashSet<>(list);
        try {
            Runtime.getRuntime().exec(stringSet.stream().iterator().next());
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
