package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.control_flow.conditional_stmt;

import com.sast.astbenchmark.common.utils.CmdUtil;
import com.sast.astbenchmark.common.utils.HttpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->流程控制语句->条件语句->if语句-else body
 * Level 2
 * Date 2024-11-10
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->流程控制语句->条件语句->if语句-else body
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/Statement_IfStatement_002_F/{cmd}
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/control_flow/conditional_stmt")
public class Statement_IfStatement_002_F {
    @GetMapping("Statement_IfStatement_002_F/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();

        try {
            if(false){
            }else{
                String cmdString = HttpUtil.doGet("www.test.com");
                CmdUtil.run(cmdString);
            }
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
