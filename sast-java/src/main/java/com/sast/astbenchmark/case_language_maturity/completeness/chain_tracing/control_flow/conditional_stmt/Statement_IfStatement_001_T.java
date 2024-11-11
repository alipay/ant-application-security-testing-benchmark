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

// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->ast对象->if语句-else body
// bind_url = completeness/chain_tracing/control_flow/conditional_stmt/Statement_IfStatement_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/control_flow/conditional_stmt")
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
