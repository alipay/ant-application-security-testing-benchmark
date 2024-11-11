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
 * Introduction 污点链路样本中的语句-if语句-condition
 * Level X
 * Date 2024-09-18
 */

// evaluation information start
// real case = false
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->ast对象->if语句-condition
// bind_url = completeness/chain_tracing/control_flow/conditional_stmt/Statement_IfStatement_008_F
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/control_flow/conditional_stmt")
public class Statement_IfStatement_008_F {
    @GetMapping("Statement_IfStatement_008_F/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a;
            if((a = cmd) != null){
                CmdUtil.run("aaa");
            }else{

            }
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
