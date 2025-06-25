
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 多函数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_value_004_T/argument_passing_value_004_T
// evaluation information end

package argument_passing_value_004_T

func argument_passing_value_004_T(__taint_src interface{}) {
	var clean string = "_"
	process2(clean)
	process1(__taint_src)
}

func process1(arg interface{}) {
	__taint_sink(arg)
}

func process2(arg interface{}) {
}

func __taint_sink(o interface{}) {
}
