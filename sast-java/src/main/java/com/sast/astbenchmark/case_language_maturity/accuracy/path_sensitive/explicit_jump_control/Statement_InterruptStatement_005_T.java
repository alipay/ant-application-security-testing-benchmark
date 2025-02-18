package com.sast.astbenchmark.case_language_maturity.accuracy.path_sensitive.explicit_jump_control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->路径敏感->跳转语句->中断语句->continue
 * Level 4+
 * Date 2024-08-16
 */
// evaluation information start
// real case = true
// evaluation item = 准确度->路径敏感->跳转语句
// bind_url = accuracy/path_sensitive/explicit_jump_control/Statement_InterruptStatement_005_T/{cmd}
// evaluation information end
@RestController()
@RequestMapping("accuracy/path_sensitive/explicit_jump_control")
public class Statement_InterruptStatement_005_T {
    @GetMapping("Statement_InterruptStatement_005_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a ="mkdir";
            for(int i=0; i<10; i++){
                a = cmd+"|";
                if(i == 5){
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
