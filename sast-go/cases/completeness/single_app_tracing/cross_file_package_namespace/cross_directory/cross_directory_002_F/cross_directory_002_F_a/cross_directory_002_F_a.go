
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 跨package
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_002_F/cross_directory_002_F_a/cross_directory_002_F_a
// evaluation information end

package main

import (
	"cross_directory_002_F/cross_directory_002_F_b"
)

func cross_directory_002_F_a(__taint_src string) {
	cross_directory_002_F_b.Cross_directory_002_F_b(__taint_src)
}

func main() {
    __taint_src := "taint_src_value"
    cross_directory_002_F_a(__taint_src)
}