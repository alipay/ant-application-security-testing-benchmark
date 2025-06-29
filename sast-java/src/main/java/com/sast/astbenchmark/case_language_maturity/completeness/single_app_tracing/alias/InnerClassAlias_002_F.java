package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.alias;

import com.sast.astbenchmark.model.alias.Inner1;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->别名
// scene introduction = InnerClassAlias-scene1
// level = 2
// bind_url = completeness/single_app_tracing/alias/InnerClassAlias_002_F
// evaluation information end
@RestController
@RequestMapping("completeness/single_app_tracing/alias")
public class InnerClassAlias_002_F {
    @PostMapping(value = "InnerClassAlias_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Inner1 a = new Inner1();
            Inner1 b = new Inner1();

            a.obj = a.new Inner2();
            b.obj = a.new Inner2();

            a.obj.set(cmd);
            String s = b.get(); // untainted

            Runtime.getRuntime().exec(s);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
