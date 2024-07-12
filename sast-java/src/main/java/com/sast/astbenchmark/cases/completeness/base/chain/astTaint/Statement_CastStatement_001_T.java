package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的语句-cast语句
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->cast语句
// compose = Statement_CastStatement_001_T.java || Statement_CastStatement_002_T.java
// bind_url = completeness/base/chain/astTaint/Statement_CastStatement_001_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Statement_CastStatement_001_T {
    @GetMapping("Statement_CastStatement_001_T/{cmd}")
    public Map<String, Object> aTaintCase0129(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Object cmdObject = new Object();
            cmdObject=cmd;
            Runtime.getRuntime().exec((String) cmdObject);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
