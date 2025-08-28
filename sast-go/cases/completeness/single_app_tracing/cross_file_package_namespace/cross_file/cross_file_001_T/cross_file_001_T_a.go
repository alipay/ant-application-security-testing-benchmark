
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨文件
// scene introduction = 跨文件
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_file/cross_file_001_T/cross_file_001_T
// evaluation information end

//两个文件都使用 package main，Go 会将它们视为同一个包
//cross_file_001_T_a.go 中调用了 cross_file_001_T_b.go 中定义的函数 Cross_file_001_T_b
//main 函数在 cross_file_001_T_a.go 中，因此它是程序入口
//Go 要求运行时列出所有 .go 文件，否则会报错，例如找不到函数
//跨文件需定义为同一个包，运行时先cd sast-go/cases/completeness/single_app_tracing/cross_file_package_namespace/cross_file/cross_file_001_T
//再执行go run cross_file_001_T_a.go cross_file_001_T_b.go

package main


func cross_file_001_T_a(__taint_src string) {
	Cross_file_001_T_b(__taint_src)
}

func main() {
	__taint_src := "taint_src_value"
	cross_file_001_T_a(__taint_src)
}