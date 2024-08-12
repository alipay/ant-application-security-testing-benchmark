package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.List;


/**
 * Introduction 污点链路样本中的语句-foreach语句
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->foreach语句
// compose = Statement_ForStatement_002_T.java
// bind_url = completeness/base/chain/astTaint/Statement_ForStatement_002_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Statement_ForStatement_002_T {
    @GetMapping("Statement_ForStatement_002_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable List<String> cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a ="mkdir";
            for(String i : cmd){
                a= i+"|";
            }
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
