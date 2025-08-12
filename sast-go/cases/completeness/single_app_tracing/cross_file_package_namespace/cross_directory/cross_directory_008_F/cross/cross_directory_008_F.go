package main

import (
	pkga "cross_directory_008_F/cross_a"
	pkgshadow "cross_directory_008_F/cross_a_shadow"
)

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 跨package4
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_008_F/cross/cross_directory_008_F
// evaluation information end

func cross_directory_008_F(__taint_src string) {
	pkga.Cross_directory_008_F(__taint_src)
	pkgshadow.Cross_directory_008_F(__taint_src)
}

func main() {
    __taint_src := "taint_src_value"
    cross_directory_008_F(__taint_src)
}