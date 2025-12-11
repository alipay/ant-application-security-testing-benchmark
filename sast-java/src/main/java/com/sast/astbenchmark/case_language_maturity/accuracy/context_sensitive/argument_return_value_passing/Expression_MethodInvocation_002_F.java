package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.argument_return_value_passing;

import com.sast.astbenchmark.common.utils.CmdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item =  准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 参数值传递->ast对象->函数调用
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/Expression_MethodInvocation_002_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/context_sensitive/argument_return_value_passing")
public class Expression_MethodInvocation_002_F {
    @GetMapping("Expression_MethodInvocation_002_F/{cmd}")
    public Map<String, Object> aTaintCase013(@PathVariable String cmd) {

        Map<String, Object> modelMap = new HashMap<>();
        cmd = "safe";
        try {
            CmdUtil.run(cmd + "|grep a");
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
