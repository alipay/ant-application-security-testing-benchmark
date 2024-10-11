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
 * Introduction 污点链路样本中的语句-if语句-if body
 * Level X
 * Date 2024-09-18
 */
// assession information start
// real vulnerability = false
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->if语句-if body
// compose = Statement_IfStatement_003_T.java && !Statement_IfStatement_004_F.java
// bind_url = completeness/base/chain/astTaint/Statement_IfStatement_004_F/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Statement_IfStatement_004_F {
    @GetMapping("Statement_IfStatement_004_F/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();

        try {
            if(true == true){
                String cmdString = HttpUtil.doGet("www.test.com");
                CmdUtil.run(cmdString);
            }else{
                CmdUtil.run(cmd);
            }
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
