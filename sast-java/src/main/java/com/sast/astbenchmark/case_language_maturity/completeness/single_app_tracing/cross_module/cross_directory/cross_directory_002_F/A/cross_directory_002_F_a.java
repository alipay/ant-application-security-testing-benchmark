package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.cross_module.cross_directory.cross_directory_002_F.A;

import com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.cross_module.cross_directory.cross_directory_002_F.B.cross_directory_002_F_b;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Introduction 完整度->基础跟踪能力->污点对象完整度->跨模块->跨目录
 * Level 2
 * Date 2024-05-09
 */
// evaluation information start
// real case = false
// evaluation item = 完整度->基础跟踪能力->污点对象完整度->跨模块->跨目录
// bind_url = completeness/single_app_tracing/cross_module/cross_directory/cross_directory_002_F/A/cross_directory_002_F_a
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/cross_module/cross_directory/cross_directory_002_F/A")
public class cross_directory_002_F_a {
    @PostMapping("cross_directory_002_F_a")
    public Map<String, Object> aTaintCase0110(@RequestParam("cmd") String cmd) {
        return new cross_directory_002_F_b().cross_directory_002_F_b(cmd);
    }
}