
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
// scene introduction = 返回值传递
// level = 2
// bind_url = completeness/single_app_tracing/function_call/return_value_passing/return_value_passing_004_T/return_value_passing_004_T
// evaluation information end

package return_value_passing_004_T

func return_value_passing_004_T(__taint_src string) {
	data := process(__taint_src)
	__taint_sink(data)
}

func __taint_sink(o interface{}) {
}

func process(__taint_src string) interface{} {
	return __taint_src
}
