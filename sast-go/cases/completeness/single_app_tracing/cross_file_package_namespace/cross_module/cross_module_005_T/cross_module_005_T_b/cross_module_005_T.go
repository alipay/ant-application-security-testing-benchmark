
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
// scene introduction = 多Main包模块化管理
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_005_T/cross_module_005_T_b/cross_module_005_T
// evaluation information end

// 先cd sast-go/cases/completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_005_T
// 在执行 go run ./cross_module_005_T_b
package main
import "os/exec"

// Go语言中，允许多个main包和main函数（只要不在同一个目录）
// 考察特性：YASA是否能否对多个main包和main函数的情况正确包管理和找到main函数

func cross_module_005_T_b(__taint_src string) {
	__taint_sink(__taint_src)
}


func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}


func main() {
	 __taint_src := "taint_src_value_main2"
	 cross_module_005_T_b(__taint_src)
}
