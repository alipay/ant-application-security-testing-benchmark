package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.explicit_jump_control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 中断语句-continue
 * Level 4
 * Date 2024-08-16
 */
// evaluation information start
// real case = false
// evaluation project = 准确度->流敏感->中断语句->continue
// bind_url = accuracy/flowSensitive/InterruptStatement/Statement_InterruptStatement_006_F/{cmd}
// evaluation information end

@RestController()
@RequestMapping("accuracy/flowSensitive/InterruptStatement")
public class Statement_InterruptStatement_006_F {
    @GetMapping("Statement_InterruptStatement_006_F/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a ="mkdir";
            for(int i=0; i<10; i++){
                a = cmd+"|";
                if(i == 9){
                    a = "ls";
                    continue;
                }
            }
            Runtime.getRuntime().exec(a);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
