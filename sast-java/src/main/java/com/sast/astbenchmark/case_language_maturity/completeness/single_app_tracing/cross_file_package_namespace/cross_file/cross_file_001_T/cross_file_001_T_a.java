package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.cross_file_package_namespace.cross_file.cross_file_001_T;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Introduction 完整度->单应用跟踪完整度->文件、包、命名空间->跨文件
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨文件
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_file/cross_file_001_T/cross_file_001_T_a
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/cross_file_package_namespace/cross_file/cross_file_001_T")
public class cross_file_001_T_a {
    @PostMapping("cross_file_001_T_a")
    public Map<String, Object> aTaintCase0110(@RequestParam("cmd") String cmd) {
        return new cross_file_001_T_b().cross_file_001_T_b(cmd);
    }
}
