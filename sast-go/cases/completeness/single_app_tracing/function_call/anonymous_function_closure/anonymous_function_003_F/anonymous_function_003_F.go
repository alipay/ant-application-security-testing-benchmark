
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 单参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/anonymous_function_003_F/anonymous_function_003_F
// evaluation information end

package anonymous_function_003_F

func anonymous_function_003_F(__taint_src interface{}) {

	process := func(input interface{}) {
		safe := "safe"
		__taint_sink(safe)
	}

	process(__taint_src)
}

func __taint_sink(o interface{}) {
}
