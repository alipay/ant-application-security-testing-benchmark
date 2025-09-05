
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 位操作->右移
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/bitwise_expression_rsh_002_F/bitwise_expression_rsh_002_F
// evaluation information end

package bitwise_expression_rsh_002_F

func bitwise_expression_rsh_002_F(__taint_src int) {
	result := __taint_src >> 1
	_ = result
	__taint_sink("aa")
}

func __taint_sink(o interface{}) {}
