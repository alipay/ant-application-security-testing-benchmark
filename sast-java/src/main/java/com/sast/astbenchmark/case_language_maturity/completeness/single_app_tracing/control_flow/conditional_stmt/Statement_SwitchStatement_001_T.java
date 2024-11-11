package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.control_flow.conditional_stmt;

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
 * Date 2024-11-10
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->链路跟踪完整度->控制流->条件语句->switch语句
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/Statement_SwitchStatement_001_T/{type}/{cmd}
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/control_flow/conditional_stmt")
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
