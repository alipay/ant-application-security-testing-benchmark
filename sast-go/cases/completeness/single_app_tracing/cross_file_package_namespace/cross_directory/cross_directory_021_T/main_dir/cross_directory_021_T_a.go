// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 同名包导入区分
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_021_T/main_dir/cross_directory_021_T_a
// evaluation information end


// 先cd到sast-go/cases/completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_021_T
// 再执行go run main_dir/cross_directory_021_T_a.go

package main
import "cross_directory_021_T/cross"

var __taint_src = "taint_src_value"

func init() {
	cross.SayHello(__taint_src)
}

func main() {
	return
}
