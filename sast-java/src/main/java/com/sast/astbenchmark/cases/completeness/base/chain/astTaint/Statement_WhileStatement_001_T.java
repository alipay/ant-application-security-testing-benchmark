package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的语句-while语句
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->while语句
// compose = Statement_WhileStatement_001_T.java
// bind_url = completeness/base/chain/astTaint/Statement_WhileStatement_001_T/{type}/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Statement_WhileStatement_001_T {
    @GetMapping("Statement_WhileStatement_001_T/{type}/{cmd}")
    public Map<String, Object> aTaintCase0118(@PathVariable String cmd, @PathVariable String type) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a ="mkdir";;
            while(StringUtils.equals(type,"mkdir")) {
                a = " "+ cmd;
            }
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
