package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import cn.hutool.http.HttpRequest;
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
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->函数调用 + BinaryOperation
// compose = Expression_MethodInvocation_Argument_002_T.java
// bind_url = completeness/base/chain/astTaint/Expression_MethodInvocation_Argument_002_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Expression_MethodInvocation_Argument_002_T {
    @PostMapping(value = "Expression_MethodInvocation_Argument_002_T")
    public Map<String, Object> aTaintCase0116(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            HttpRequest.post("http://localhost:39100/ataint/case00124/2?cmd="+cmd)
                    .execute();
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
