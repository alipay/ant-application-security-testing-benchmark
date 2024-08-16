package com.sast.astbenchmark.cases.completeness.base.chain.astTaint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的语句-try语句-污点传递发生在try中
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->try语句-污点传递发生在try中
// compose = !Statement_TryStatement_003_F.java && Statement_TryStatement_002_T.java
// bind_url = completeness/base/chain/astTaint/Statement_TryStatement_002_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Statement_TryStatement_003_F {
    @GetMapping("Statement_TryStatement_002_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            int[] data = new int[10];
            if (data[15] > 0) {
                cmd = "";
            }else{
                throw new IOException(cmd);
            }
        } catch (IndexOutOfBoundsException | IOException ex) {
            try {
                Runtime.getRuntime().exec(ex.getMessage());
                modelMap.put("status", "success");
            } catch (Exception e) {
                modelMap.put("status", "error");
            }
        }
        return modelMap;
    }
}
