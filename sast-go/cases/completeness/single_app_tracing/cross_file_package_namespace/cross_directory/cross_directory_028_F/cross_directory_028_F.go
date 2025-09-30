// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 同名包路径区分
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_028_F/cross_directory_028_F
// evaluation information end

// 先cd sast-go/cases/completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_028_F
// 再执行 go run cross_directory_028_F.go

package main

// Go语言中，一个包以文件结构路径唯一标识。允许同名包。
// --以包名作为key来进行包管理，导致同名包丢失。

// 考察特性：---的**包管理逻辑**(this.packageManager)，是否能够区分并保存同名包
func cross_directory_028_F(__taint_src string) {
	cross_same_name_028_F.Fun(__taint_src)
}

func main() {
	__taint_src := "taint_src_value"
	cross_directory_028_F(__taint_src)
}
