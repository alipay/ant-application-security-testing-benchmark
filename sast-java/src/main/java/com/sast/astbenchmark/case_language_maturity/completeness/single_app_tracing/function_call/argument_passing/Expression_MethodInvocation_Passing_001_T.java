package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.function_call.argument_passing;

import com.sast.astbenchmark.common.utils.CmdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 函数调用
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/Expression_MethodInvocation_Passing_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/function_call/argument_passing")
public class Expression_MethodInvocation_Passing_001_T {
    @GetMapping("Expression_MethodInvocation_Passing_001_T/{cmd}")
    public Map<String, Object> aTaintCase013(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            CmdUtil.run(cmd + "|grep a");
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
