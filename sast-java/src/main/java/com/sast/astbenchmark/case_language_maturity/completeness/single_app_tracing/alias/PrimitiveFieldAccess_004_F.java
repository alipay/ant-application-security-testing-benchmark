package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.alias;

import com.sast.astbenchmark.model.alias.PrimitiveData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->别名->PrimitiveFieldAccess-scene2
 * Level 2
 * Date 2024-07-02
 */
@RestController
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->别名->PrimitiveFieldAccess-scene2
// bind_url = completeness/single_app_tracing/alias/PrimitiveFieldAccess_004_F
// evaluation information end
@RequestMapping("completeness/single_app_tracing/alias")
public class PrimitiveFieldAccess_004_F {
    @PostMapping(value = "PrimitiveFieldAccess_004_F")
    public Map<String, Object> testcase(@RequestParam int id) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            PrimitiveData i = new PrimitiveData();
            PrimitiveData b = i;
            b.setIntData(100);
            Runtime.getRuntime().exec("cat /some/path/" + i.intData + ".png");
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}