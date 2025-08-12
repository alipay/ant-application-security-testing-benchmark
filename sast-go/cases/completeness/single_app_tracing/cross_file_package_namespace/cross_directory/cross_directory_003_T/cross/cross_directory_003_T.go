package main

import (
	"cross_directory_003_T/cross_01/cross_02"
)
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 跨package
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_003_T/cross/cross_directory_003_T
// evaluation information end


func cross_directory_003_T(__taint_src string) {
	cross_pkg_deep2.Cross_directory_003_T_a(__taint_src)
}


func main() {
    __taint_src := "taint_src_value"
    cross_directory_003_T(__taint_src)
}