
// evaluation information start
// real case = true
// evaluation item =完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 单参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/anonymous_function_004_T/anonymous_function_004_T
// evaluation information end

package anonymous_function_004_T

func anonymous_function_004_T(__taint_src interface{}) {

	process := func(input interface{}) {
		__taint_sink(input)
	}

	process(__taint_src)
}

func __taint_sink(o interface{}) {
}
