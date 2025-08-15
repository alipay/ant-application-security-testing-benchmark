
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
// scene introduction = 跨module
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_001_T/cross_module_001_T_b/cross_module_001_T_b
// evaluation information end

package main

import (
	"gotest.com/cross_module_001_T_a"
)

func cross_module_001_T_b(__taint_src string) {
	cross_module_001_T_a.Cross_module_001_T_a(__taint_src)
}

func main() {
    __taint_src := "taint_src_value"
    cross_module_001_T_b(__taint_src)
}