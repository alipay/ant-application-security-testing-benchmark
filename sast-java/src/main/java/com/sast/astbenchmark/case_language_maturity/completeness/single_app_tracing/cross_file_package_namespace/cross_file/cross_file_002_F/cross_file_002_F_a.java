package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.cross_file_package_namespace.cross_file.cross_file_002_F;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨文件
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_file/cross_file_002_F/cross_file_002_F_a
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/cross_file_package_namespace/cross_file/cross_file_002_F")
public class cross_file_002_F_a {
    @PostMapping("cross_file_002_F_a")
    public Map<String, Object> aTaintCase0110(@RequestParam("cmd") String cmd) {
        return new cross_file_002_F_b().cross_file_002_F_b(cmd);
    }
}
