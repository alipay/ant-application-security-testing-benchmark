package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import com.sast.astbenchmark.common.utils.CmdUtil;
import com.sast.astbenchmark.common.utils.HttpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-中缀表达式+函数调用表达式
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->函数调用+中缀表达式
// compose = Expression_MethodInvocation_InfixExpression_001_T.java
// bind_url = completeness/base/chain/astTaint/Expression_MethodInvocation_InfixExpression_001_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Expression_MethodInvocation_InfixExpression_001_T {
    @GetMapping("Expression_MethodInvocation_InfixExpression_001_T/{cmd}")
    public Map<String, Object> aTaintCase014(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();

        try {
            CmdUtil.run(cmd+ HttpUtil.doGet("www.test.com"));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
