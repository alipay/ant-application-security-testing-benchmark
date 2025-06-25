package com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.cross_file_package_namespace.cross_directory.cross_directory_002_F.A;

import com.sast.astbenchmark.case_language_maturity.completeness.single_app_tracing.cross_file_package_namespace.cross_directory.cross_directory_002_F.B.cross_directory_002_F_b;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 跨目录
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_002_F/A/cross_directory_002_F_a
// evaluation information end

@RestController()
@RequestMapping("completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_002_F/A")
public class cross_directory_002_F_a {
    @PostMapping("cross_directory_002_F_a")
    public Map<String, Object> aTaintCase0110(@RequestParam("cmd") String cmd) {
        return new cross_directory_002_F_b().cross_directory_002_F_b(cmd);
    }
}