package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.function_call.override;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sast.astbenchmark.model.custom.PS;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-多态
 * Level 2
 * Date 2024-08-16
 */

// evaluation information start
// real case = false
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->ast对象->多态
// bind_url = completeness/chain_tracing/function_call/override/Expression_Polymorphism_002_F
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/function_call/override")
public class Expression_Polymorphism_002_F {
    @GetMapping("Expression_Polymorphism_002_F/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            PS ps = new PS(cmd, "~");
            Runtime.getRuntime().exec(ps.getCmd("ls"));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
