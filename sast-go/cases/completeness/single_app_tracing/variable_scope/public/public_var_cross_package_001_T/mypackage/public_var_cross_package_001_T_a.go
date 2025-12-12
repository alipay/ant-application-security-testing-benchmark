// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->变量作用域->public变量
// scene introduction = Public变量跨包访问
// level = 2
// bind_url = completeness/single_app_tracing/variable_scope/public/public_var_cross_package_001_T/mypackage/public_var_cross_package_001_T_a
// date = 2025-12-01 15:25:25
// evaluation information end

package mypackage

// Public变量（首字母大写，导出变量）
var PublicVar string

// 为public变量赋值的函数
func SetPublicVar(__taint_src string) {
	// 场景特点：在不同包中为public变量赋值
	PublicVar = __taint_src
}
