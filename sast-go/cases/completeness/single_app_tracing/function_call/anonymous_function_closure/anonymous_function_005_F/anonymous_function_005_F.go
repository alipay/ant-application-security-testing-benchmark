
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 无参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/anonymous_function_005_F/anonymous_function_005_F
// evaluation information end

package anonymous_function_005_F

func anonymous_function_005_F(__taint_src interface{}) {
	func() {
		a := __taint_src
		a = "safe"
		__taint_sink(a)
	}()
}

func __taint_sink(o interface{}) {
}
