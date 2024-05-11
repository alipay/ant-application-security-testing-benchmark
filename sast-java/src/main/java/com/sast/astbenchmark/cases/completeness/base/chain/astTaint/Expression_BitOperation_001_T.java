package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-位运算
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->位运算操作
// compose = Expression_BitOperation_001_T.java
// bind_url = completeness/base/chain/astTaint/Expression_BitOperation_001_T
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Expression_BitOperation_001_T {
    @PostMapping(value = "Expression_BitOperation_001_T")
    public Map<String, Object> aTaintCase0159(@RequestParam char cmd ) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            cmd= (char) (cmd<<1);
            Runtime.getRuntime().exec(String.valueOf(cmd));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
