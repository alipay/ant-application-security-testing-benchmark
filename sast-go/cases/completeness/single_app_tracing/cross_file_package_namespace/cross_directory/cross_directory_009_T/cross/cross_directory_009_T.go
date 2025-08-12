package main

import (
	pkg "cross_directory_009_T/cross_init"
)

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 跨package5
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_009_T/cross/cross_directory_009_T
// evaluation information end

func cross_directory_009_T(__taint_src string) {
	pkg.Arg = __taint_src
	pkg.Cross_directory_009_T("_")
}

func main() {
    __taint_src := "taint_src_value"
    cross_directory_009_T(__taint_src)
}