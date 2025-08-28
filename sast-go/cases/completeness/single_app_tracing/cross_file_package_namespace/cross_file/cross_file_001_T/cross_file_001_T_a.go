
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨文件
// scene introduction = 跨文件
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_file/cross_file_001_T/cross_file_001_T
// evaluation information end


package main


func cross_file_001_T_a(__taint_src string) {
	Cross_file_001_T_b(__taint_src)
}

func main() {
	__taint_src := "taint_src_value"
	cross_file_001_T_a(__taint_src)
}