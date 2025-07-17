package main

import (
	pkga "cross_directory_007_T/cross_a"
	pkgshadow "cross_directory_007_T/cross_a_shadow"
)

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 跨package4
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_007_T/cross/cross_directory_007_T
// evaluation information end

func cross_directory_007_T(__taint_src string) {
	//有污点传播链路
	pkga.Cross_directory_007_T(__taint_src)
	//无污点链路，污点传播中断
	pkgshadow.Cross_directory_007_T(__taint_src)
}
