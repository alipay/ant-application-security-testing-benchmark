
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨文件
// scene introduction = 跨文件
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_file/cross_file_001_T/cross_file_001_T
// evaluation information end



package main

import "os/exec"

func Cross_file_001_T_b(o interface{}) {
	__taint_sink(o)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}