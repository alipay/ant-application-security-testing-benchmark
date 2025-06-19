package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.control_flow.conditional_stmt;

import com.sast.astbenchmark.common.utils.CmdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句
// scene introduction = switch语句
// level = 2
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/Statement_SwitchStatement_002_F/{type}/{cmd}
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/control_flow/conditional_stmt")
public class Statement_SwitchStatement_002_F {
    @GetMapping("Statement_SwitchStatement_002_F/{type}/{cmd}")
    public Map<String, Object> aTaintCase016(@PathVariable String cmd, @PathVariable String type) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            switch (type) {

                case "ls":
                    CmdUtil.run("mkdir" + " " + cmd);
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
