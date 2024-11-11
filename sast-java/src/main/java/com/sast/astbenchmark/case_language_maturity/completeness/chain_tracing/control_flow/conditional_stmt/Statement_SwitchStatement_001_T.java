package com.sast.astbenchmark.case_language_maturity.completeness.chain_tracing.control_flow.conditional_stmt;

import com.sast.astbenchmark.common.utils.CmdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的语句-switch语句
 * Level X
 * Date 2024-05-09
 */

// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点链路完整度->ast对象->switch语句
// bind_url = completeness/chain_tracing/control_flow/conditional_stmt/Statement_SwitchStatement_001_T
// evaluation information end
@RestController()
@RequestMapping("completeness/chain_tracing/control_flow/conditional_stmt")
public class Statement_SwitchStatement_001_T {
    @GetMapping("Statement_SwitchStatement_001_T/{type}/{cmd}")
    public Map<String, Object> aTaintCase016(@PathVariable String cmd, @PathVariable String type) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            switch (type){

                case "mkdir":
                    CmdUtil.run("mkdir"+" "+cmd);
                    modelMap.put("status", "success");
                default:
                    modelMap.put("status", "success");
                    return null;
            }

        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
