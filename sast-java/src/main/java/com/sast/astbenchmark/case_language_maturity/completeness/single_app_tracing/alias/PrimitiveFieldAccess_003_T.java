package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.alias;

import com.sast.astbenchmark.model.alias.PrimitiveData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
// evaluation information start
// real case = true
// evaluation item =  完整度->单应用跟踪完整度->别名
// scene introduction = PrimitiveFieldAccess-scene2
// level = 2
// bind_url = completeness/single_app_tracing/alias/PrimitiveFieldAccess_003_T
// evaluation information end
@RequestMapping("completeness/single_app_tracing/alias")
public class PrimitiveFieldAccess_003_T {
    @PostMapping(value = "PrimitiveFieldAccess_003_T")
    public Map<String, Object> testcase(@RequestParam int id) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            id = id + 99;
            PrimitiveData i = new PrimitiveData();
            PrimitiveData b = i;
            b.setIntData(id);
            Runtime.getRuntime().exec("cat /some/path/" + i.intData + ".png");
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}