
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 二元运算->加
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/binary_expression_add_002_F/binary_expression_add_002_F
// evaluation information end

package binary_expression_add_002_F

func binary_expression_add_002_F(__taint_src string) {
	result := __taint_src + "_"
	result = "aa"
	__taint_sink(result)
}

func __taint_sink(o interface{}) {}
