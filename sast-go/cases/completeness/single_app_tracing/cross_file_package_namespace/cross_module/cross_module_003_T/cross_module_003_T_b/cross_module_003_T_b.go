
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->文件、包、命名空间->跨模块
// scene introduction = 跨module-别名
// level = 2
// bind_url = completeness/single_app_tracing/cross_file_package_namespace/cross_module/cross_module_003_T/cross_module_003_T_b/cross_module_003_T_b
// evaluation information end

package cross_module_003_T_b

import (
	Mymod "gotest.com/cross_module_003_T_a" //引用自定义模块
)

func cross_module_003_T_b(__taint_src string) {
	Mymod.Cross_module_003_T_a(__taint_src)
}
