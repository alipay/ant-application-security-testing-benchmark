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
 * Introduction 污点链路样本中的语句-if-then语句
 * Level X
 * Date 2024-07-31
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->if-then语句
// compose = Statement_IfStatement_002_T.java
// bind_url = completeness/base/chain/astTaint/Statement_IfStatement_002_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Statement_IfStatement_002_T {
    @GetMapping("Statement_IfStatement_002_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();

        try {
            int a = 5;
            if(a < 5){
                CmdUtil.run(cmd);
            }else if(a > 5){
                cmd = "untainted";
                CmdUtil.run(cmd);
            }else{
                String cmdString = HttpUtil.doGet("www.test.com");
                CmdUtil.run(cmd+cmdString);
            }
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
