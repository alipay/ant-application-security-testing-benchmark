package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-函数调用+参数获取
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->函数调用+参数获取
// compose = Expression_MethodInvocation_Argument_001_T.java || Expression_MethodInvocation_Argument_002_T.java
// bind_url = completeness/base/chain/astTaint/Expression_MethodInvocation_Argument_001_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Expression_MethodInvocation_Argument_001_T {
    @PostMapping(value = "Expression_MethodInvocation_Argument_001_T")
    public Map<String, Object> aTaintCase0115(@RequestParam String cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            char[] chars= new char[]{0,0};
            cmd.getChars(0,2,chars,0);
            Runtime.getRuntime().exec(String.valueOf(chars));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }

}
