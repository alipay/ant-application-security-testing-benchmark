package com.sast.astbenchmark.case_language_maturity.completeness.object_tracing.datatype.primitives;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 对象中的简单类型对象，int作为污点
 * Level 2
 * Date 2024-08-16
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->java原生对象->int
// bind_url = completeness/base/object/javaNative/Base_Integer_008_F/{cmd}
// evaluation information end

@RestController()
@RequestMapping("completeness/base/object/javaNative")
public class Base_Integer_008_F {
    @PostMapping("Base_Integer_008_F")
    public Map<String, Object> testcase(@PathVariable int cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        int i = 0;
        try {
            Runtime.getRuntime().exec(String.valueOf(i));
            modelMap.put("status", "success");
        }
        catch (Exception e){
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
