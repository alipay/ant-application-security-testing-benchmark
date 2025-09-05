
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨文件
// scene introduction = 跨文件
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_file/cross_file_002_F/cross_file_002_F
// evaluation information end

package main

func Cross_file_002_F_a(__taint_src string) {
	Cross_file_002_F_b(__taint_src)
}

func main() {
    __taint_src := "taint_src_value"
    cross_file_002_F_a(__taint_src)
}