package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.alias;

import com.sast.astbenchmark.model.alias.A;
import com.sast.astbenchmark.model.alias.B;
import com.sast.astbenchmark.model.alias.SimpleAlias;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->别名
// scene introduction = 别名参数
// level = 2
// bind_url = completeness/single_app_tracing/alias/SameArgumentAlias_002_F
// evaluation information end
@RestController
@RequestMapping("completeness/single_app_tracing/alias")
public class SameArgumentAlias_002_F {
    @PostMapping(value = "SameArgumentAlias_002_F")
    public Map<String, Object> testcase(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            SimpleAlias sa = new SimpleAlias();
            B b1 = new B();
            B b2 = new B();
            A a = new A();
            a.b = cmd;

            sa.foo(b1, b2);
            String s = sa.bar(a).b;

            Runtime.getRuntime().exec(s);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}