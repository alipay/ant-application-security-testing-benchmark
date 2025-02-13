package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.polymorphism;

import com.sast.astbenchmark.model.custom.PS;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 准确度->上下文敏感分析->多态
 * Level 2
 * Date 2024-08-16
 */
// evaluation information start
// real case = false
// evaluation item =  准确度->上下文敏感分析->多态
// bind_url = accuracy/context_sensitive/polymorphism/Expression_Polymorphism_002_F
// evaluation information end
@RestController()
@RequestMapping("accuracy/context_sensitive/polymorphism")
public class Expression_Polymorphism_002_F {
    @GetMapping("Expression_Polymorphism_002_F/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {

        Map<String, Object> modelMap = new HashMap<>();
        try {
            PS ps = new PS(cmd, "~");
            Runtime.getRuntime().exec(ps.getCmd("ls"));
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
