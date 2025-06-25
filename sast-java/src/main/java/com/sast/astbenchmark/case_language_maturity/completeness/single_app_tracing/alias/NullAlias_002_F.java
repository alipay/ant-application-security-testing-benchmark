package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.alias;

import com.sast.astbenchmark.model.alias.DataClass;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->别名
// scene introduction = NullAlias
// level = 2
// bind_url = completeness/single_app_tracing/alias/NullAlias_002_F
// evaluation information end
@RestController
@RequestMapping("completeness/single_app_tracing/alias")
public class NullAlias_002_F {
    @PostMapping(value = "NullAlias_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            DataClass dc = new DataClass();
            DataClass dc2 = null;
            DataClass dc3 = new DataClass();

            dc2 = dc3;

            dc2.next = dc;
            dc2 = null;
            dc3.next = null;

            String a = cmd;
            dc.data = a;
            dc = null;

            Runtime.getRuntime().exec(dc3.next.data);  // NPE
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
