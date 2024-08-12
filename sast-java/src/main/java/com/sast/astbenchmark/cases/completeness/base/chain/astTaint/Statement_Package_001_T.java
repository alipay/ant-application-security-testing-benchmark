package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的表达式-变量赋值直接引入而不是import
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->变量赋值直接引入而不是import
// compose = Statement_Package_001_T.java
// bind_url = completeness/base/chain/astTaint/Statement_Package_001_T
// assession information end
@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Statement_Package_001_T {
    @GetMapping("Statement_Package_001_T/{url}")
    public Map<String, Object> aTaintCase0157(@PathVariable String url) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String result = com.sast.astbenchmark.common.utils.HttpUtil.doGet(url);
            modelMap.put("status", "success");
            modelMap.put("result", result);
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
