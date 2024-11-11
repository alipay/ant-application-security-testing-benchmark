package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.argument_value_passing;

import com.sast.astbenchmark.common.utils.CmdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-中缀表达式样本
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->ast对象->函数调用
// bind_url = accuracy/context_sensitive/argument_value_passing/Expression_MethodInvocation_001_T
// evaluation information end
@RestController()
@RequestMapping("accuracy/context_sensitive/argument_value_passing")
public class Expression_MethodInvocation_001_T {
    @GetMapping("Expression_MethodInvocation_001_T/{cmd}")
    public Map<String, Object> aTaintCase013(@PathVariable String cmd) {

        Map<String, Object> modelMap = new HashMap<>();
        try {
            CmdUtil.run(cmd+"|grep a");
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
