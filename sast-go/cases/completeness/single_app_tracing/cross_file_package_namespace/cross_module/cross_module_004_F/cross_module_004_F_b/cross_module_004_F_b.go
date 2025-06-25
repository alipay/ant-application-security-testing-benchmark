
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
// scene introduction = 跨module-别名
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_004_F/cross_module_004_F_b/cross_module_004_F_b
// evaluation information end

package cross_module_004_F_b

import (
	Mymod "gotest.com/cross_module_004_F_a"
)

func cross_module_004_F_b(__taint_src string) {
	Mymod.Cross_module_004_F_a(__taint_src)
}
