
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->基础表达式
// scene introduction = 关系操作->等于
// level = 2
// bind_url = completeness/single_app_tracing/expression/basic_expression_operation/relation_expression_equal_002_F/relation_expression_equal_002_F
// evaluation information end

package relation_expression_equal_002_F

func relation_expression_equal_002_F(__taint_src string) {
	result := __taint_src == "__taint_src"
	result = false
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
}
