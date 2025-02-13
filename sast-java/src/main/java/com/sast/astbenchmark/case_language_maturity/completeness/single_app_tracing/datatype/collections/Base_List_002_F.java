package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.collections;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->数据类型和结构->集合->List
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->集合->List
// bind_url = completeness/base/object/javaNative/Base_List_002_F
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_List_002_F {
    @PostMapping("Base_List_002_F")
    public Map<String, Object> aTaintCase0141(@RequestBody List<String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        List<String> list = Arrays.asList("a", "b", "c");
        try {
            Runtime.getRuntime().exec(list.get(0));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
