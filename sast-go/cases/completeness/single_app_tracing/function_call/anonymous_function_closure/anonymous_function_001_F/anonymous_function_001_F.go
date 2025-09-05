
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 返回值传递
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/anonymous_function_001_F/anonymous_function_001_F
// evaluation information end

package anonymous_function_001_F

func anonymous_function_001_F(__taint_src string) {
	process := func(a string, b string) string {
		return "safe"
	}
	__taint_sink(process(__taint_src, "foo"))
}

func __taint_sink(o interface{}) {
}
