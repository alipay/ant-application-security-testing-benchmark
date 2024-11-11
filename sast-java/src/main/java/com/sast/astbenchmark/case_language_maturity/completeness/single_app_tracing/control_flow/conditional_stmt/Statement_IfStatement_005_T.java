package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.control_flow.conditional_stmt;

import com.sast.astbenchmark.common.utils.CmdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 污点链路样本中的语句-if语句-elseif body
 * Level X
 * Date 2024-11-10
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->链路跟踪完整度->控制流->条件语句->if语句-elseif body
// bind_url = completeness/single_app_tracing/control_flow/conditional_stmt/Statement_IfStatement_005_T/{cmd}
// evaluation information end
@RestController()
@RequestMapping("completeness/single_app_tracing/control_flow/conditional_stmt")
public class Statement_IfStatement_005_T {
    @GetMapping("Statement_IfStatement_005_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();

        try {
            int a = 5;
            if(a < 5){

            }else if(a == 5){
                CmdUtil.run(cmd);
            }else{
                cmd="ls";
            }
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
