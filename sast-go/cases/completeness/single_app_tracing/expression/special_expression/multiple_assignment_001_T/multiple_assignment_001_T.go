
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 表达式并行赋值
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/multiple_assignment_001_T/multiple_assignment_001_T
// evaluation information end

package multiple_assignment_001_T

func multiple_assignment_001_T(__taint_src string) {
	_, _, result := "_", "_", __taint_src
	__taint_sink(result)
}

func __taint_sink(o interface{}) {
}
