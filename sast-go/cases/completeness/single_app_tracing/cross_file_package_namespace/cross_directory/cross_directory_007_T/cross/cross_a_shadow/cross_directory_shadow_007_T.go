// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 跨package
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_007_T/cross/cross_a_shadow/cross_directory_shadow_007_T
// evaluation information end

package pkgshadow

import "os/exec"
// 在Go 1.14.7中需要手动启用模块功能:export GO111MODULE=on
//Go 模块机制要求每个被 replace 指向的路径必须是一个有效的模块（即包含 go.mod 文件）。即使这些子包是本地的，也需要为它们定义模块路径。
func Cross_directory_shadow_007_T(o interface{}) {
	__taint_sink("_")
}


func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

