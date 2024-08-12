package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-变量声明
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->变量声明
// compose = Expression_VariableDeclaration_001_T.java
// bind_url = completeness/base/chain/astTaint/Expression_VariableDeclaration_001_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Expression_VariableDeclaration_001_T {
    @PostMapping("Expression_VariableDeclaration_001_T")
    public Map<String, Object> aTaintCase019(@RequestParam String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        char[] data = cmd.toCharArray();
        try {
            Runtime.getRuntime().exec(new String(data));
            modelMap.put("status", "success");
        } catch (IOException e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
