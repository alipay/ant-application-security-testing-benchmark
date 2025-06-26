package com.sast.astbenchmark.case_language_maturity.accuracy.context_sensitive.polymorphism;

import com.sast.astbenchmark.model.custom.PS;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// evaluation information start
// real case = true
// evaluation item =  准确度->上下文敏感分析->多态
// scene introduction = 
// level = 2
// bind_url = accuracy/context_sensitive/polymorphism/Expression_Polymorphism_001_T/{cmd}
// evaluation information end
@RestController()
@RequestMapping("accuracy/context_sensitive/polymorphism")
public class Expression_Polymorphism_001_T {
    @GetMapping("Expression_Polymorphism_001_T/{cmd}")
    public Map<String, Object> testcase(@PathVariable String cmd) {

        Map<String, Object> modelMap = new HashMap<>();
        try {
            PS ps = new PS(cmd, "~");
            Runtime.getRuntime().exec(ps.getCmd());
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}
