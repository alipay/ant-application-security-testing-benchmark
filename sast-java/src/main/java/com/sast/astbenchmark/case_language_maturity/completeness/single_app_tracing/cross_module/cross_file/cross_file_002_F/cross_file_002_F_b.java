package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.cross_module.cross_file.cross_file_002_F;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Introduction 完整度->基础跟踪能力->污点对象完整度->跨模块->跨文件
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->跨模块->跨文件
// bind_url = completeness/single_app_tracing/cross_module/cross_file/cross_file_002_F/cross_file_002_F_b
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/cross_module/cross_file/cross_file_002_F")
public class cross_file_002_F_b {
    public Map<String, Object> cross_file_002_F_b(String cmd) {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            Runtime.getRuntime().exec("_");
            modelMap.put("status", "success");
        } catch (Exception e) {
            modelMap.put("status", "error");
        }
        return modelMap;
    }
}