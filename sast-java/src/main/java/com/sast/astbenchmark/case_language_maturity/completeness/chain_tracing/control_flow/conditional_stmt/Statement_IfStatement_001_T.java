package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.control_flow.conditional_stmt;

import com.sast.astbenchmark.common.utils.CmdUtil;
import com.sast.astbenchmark.common.utils.HttpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的语句-if语句-else body
 * Level X
 * Date 2024-05-09
 */
// assession information start
// real vulnerability = true
// assession project = 完整度->基础跟踪能力->污点链路完整度->ast对象->if语句-else body
// compose = Statement_IfStatement_001_T.java && !Statement_IfStatement_002_F.java
// bind_url = completeness/base/chain/astTaint/Statement_IfStatement_001_T/{cmd}
// assession information end

@RestController()
@RequestMapping("completeness/base/chain/astTaint")
public class Statement_IfStatement_001_T {
    @GetMapping("Statement_IfStatement_001_T/{cmd}")
    public Map<String, Object> aTaintCase015(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();

        try {
            if(false){
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
