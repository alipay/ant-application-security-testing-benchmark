package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.cross_module.cross_directory.cross_directory_001_T.B;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->基础跟踪能力->污点对象完整度->跨模块->跨目录
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->跨模块->跨目录
// bind_url = completeness/single_app_tracing/cross_module/cross_directory/cross_directory_001_T/B/cross_directory_001_T_b
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/cross_module/cross_directory/cross_directory_001_T/B")
public class cross_directory_001_T_b {
    public Map<String, Object> cross_directory_001_T_b(String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec(cmd);
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}