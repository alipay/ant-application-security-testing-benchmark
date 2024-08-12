package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的语句-try语句 隐式触发异常
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->try语句 隐式触发异常
// compose = Statement_TryStatement_002_T.java
// bind_url = completeness/base/chain/astTaint/Statement_TryStatement_002_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Statement_TryStatement_004_T {
    @GetMapping("Statement_TryStatement_002_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        String a = "";
        try {
            a = cmd;
            int[] data = new int[10];
            if (data[15] > 0) {
                a = "";
            }else{
                throw new IOException();
            }
        } catch (IndexOutOfBoundsException | IOException ex) {
            try {
                Runtime.getRuntime().exec(a);
                modelMap.put("status", "success");
            } catch (IOException e) {
                modelMap.put("status", "error");
            }
        }
        return modelMap;
    }
}
