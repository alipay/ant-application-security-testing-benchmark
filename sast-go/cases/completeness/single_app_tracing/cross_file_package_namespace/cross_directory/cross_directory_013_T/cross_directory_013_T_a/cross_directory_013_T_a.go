// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = replace包层级调用链
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_013_T/cross_directory_013_T_a/cross_directory_013_T_a
// evaluation information end

// 这里有两个go.mod文件 cross_directory_013_T文件夹下的go.mod文件是负责"指路"（replace指令），当看到 import "cross/other/cross_01" 时
// 不要去其他地方寻找 应该去本地的 .cross/other/cross_01 目录找，cross_01文件夹下的go.mod文件是"亮明身份"，告诉go模块 我确实是你要找的文件。
// 执行跨模块文件时需先cd到sast-go/cases/completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_013_T
// 再执行go run cross_directory_013_T_a/cross_directory_013_T_a.go
package main
import "cross/other/cross_01"

func cross_directory_013_T_a(__taint_src string) {
	cross_directory_013_T_b.SayHello(__taint_src)
}

func main() {
    __taint_src := "taint_src_value"
    cross_directory_013_T_a(__taint_src)
}