package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.datatype.generics;

import com.sast.astbenchmark.model.custom.G;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->数据类型和结构->指针->泛型类-类型参数
 * Level 2
 * Date 2024-08-16
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->指针->泛型类-类型参数
// bind_url = completeness/single_app_tracing/datatype/generics/CallExpression_CustomCode_Class_010_F
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/datatype/generics")
public class CallExpression_CustomCode_Class_010_F {
    @PostMapping(value = "CallExpression_CustomCode_Class_010_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            G<String> a = new G<>("ls");
            Runtime.getRuntime().exec(a.getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
