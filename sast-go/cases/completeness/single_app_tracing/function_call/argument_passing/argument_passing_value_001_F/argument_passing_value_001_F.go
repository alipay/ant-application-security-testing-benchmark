
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 普通
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_value_001_F/argument_passing_value_001_F
// evaluation information end

package argument_passing_value_001_F

func argument_passing_value_001_F(__taint_src interface{}) {
	process(__taint_src)
}

func process(arg interface{}) {
	arg = "safe"
	__taint_sink(arg)
}

func __taint_sink(o interface{}) {
}
