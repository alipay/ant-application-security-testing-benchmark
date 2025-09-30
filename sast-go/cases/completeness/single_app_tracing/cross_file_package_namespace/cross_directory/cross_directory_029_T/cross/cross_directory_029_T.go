// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 识别导入根目录
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_029_T/cross/cross_directory_029_T
// evaluation information end

// 先cd到sast-go/cases/completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_029_T/cross
// 再执行go run cross_directory_029_T.go

package main

import (
	"os/exec"
)

// Go语言中的import： import 项目名(代表根目录)/目录名1/目录名2/目录名3
// 所谓的根目录 指 go.mod所在的目录

func cross_directory_029_T(__taint_src string) {
	value := cross_directory_029_T_a.Person{}.Skiing(__taint_src) // 看这些符号值能不能被解析出来
	__taint_sink(value)
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	cross_directory_029_T(__taint_src)
}
