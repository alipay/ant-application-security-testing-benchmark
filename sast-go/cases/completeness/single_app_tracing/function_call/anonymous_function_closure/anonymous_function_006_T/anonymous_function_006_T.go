
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 无参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/anonymous_function_006_T/anonymous_function_006_T
// evaluation information end

package anonymous_function_006_T

func anonymous_function_006_T(__taint_src interface{}) {
	func() {
		__taint_sink(__taint_src)
	}()
}

func __taint_sink(o interface{}) {
}
