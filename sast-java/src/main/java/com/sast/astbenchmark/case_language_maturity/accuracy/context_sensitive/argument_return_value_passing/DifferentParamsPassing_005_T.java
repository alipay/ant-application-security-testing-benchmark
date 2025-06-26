package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.argument_return_value_passing;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item =  准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 参数值传递->VarargArrayAccess
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/DifferentParamsPassing_005_T
// evaluation information end
@RestController()
@RequestMapping("accuracy/context_sensitive/argument_return_value_passing")
public class DifferentParamsPassing_005_T {
    @PostMapping("DifferentParamsPassing_005_T")
    public Map<String, Object> testcase(@RequestParam String cmd) {

        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a = Invoke.chooseOne(3, "a", "b", "c", cmd, "e");
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

    private static class Invoke {
        private static String chooseOne(int i, String... params) {
            return params[i];
        }
    }
}