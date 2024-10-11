package com.sast.astbenchmark.cases.accuracy.flowSensitive.InterruptStatement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 中断语句-break
 * Level X
 * Date 2024-08-16
 */
// assession information start
// real vulnerability = true
// assession project = 准确度->流敏感->中断语句->break
// compose = Statement_InterruptStatement_001_T.java && !Statement_InterruptStatement_002_F.java
// bind_url = accuracy/flowSensitive/InterruptStatement/Statement_InterruptStatement_001_T/{cmd}
// assession information end

@RestController()
@RequestMapping("accuracy/flowSensitive/InterruptStatement")
public class Statement_InterruptStatement_001_T {
    @GetMapping("Statement_InterruptStatement_001_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            String a ="mkdir";
            for(int i=0; i<10; i++){
                a = cmd+"|";
                if(i == 5){
                    a += "ls";
                    break;
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
