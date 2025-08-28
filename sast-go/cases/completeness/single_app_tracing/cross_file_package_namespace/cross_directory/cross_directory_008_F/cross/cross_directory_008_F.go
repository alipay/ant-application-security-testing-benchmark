package main

import (
	pkga "cross_directory_008_F/cross_a"
	pkgshadow "cross_directory_008_F/cross_a_shadow"
)

// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨包
// scene introduction = 跨package4
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_008_F/cross/cross_directory_008_F
// evaluation information end

// 在Go 1.14.7中需要手动启用模块功能:export GO111MODULE=on,在 Go 1.16 及后续版本中，Go 模块功能已默认启用，无需手动设置
//Go 模块机制要求每个被 replace 指向的路径必须是一个有效的模块（即包含 go.mod 文件）。即使这些子包是本地的，也需要为它们定义模块路径。
//在go文件中规范文件夹下有go.mod为一个模块，
//执行跨模块文件时需先cd到sast-go/cases/completeness/single_app_tracing/cross_file_package_namespace/cross_directory/cross_directory_008_F
//再执行go run cross/cross_directory_008_F.go

func cross_directory_008_F(__taint_src string) {
	pkga.Cross_directory_a_008_F(__taint_src)
	pkgshadow.Cross_directory_shadow_008_F(__taint_src)
}

func main() {
    __taint_src := "taint_src_value"
    cross_directory_008_F(__taint_src)
}
