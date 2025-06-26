
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 参数顺序
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_value_006_T/argument_passing_value_006_T
// evaluation information end

package argument_passing_value_006_T

func argument_passing_value_006_T(__taint_src string) {
	process(__taint_src, "_")
}

func process(arg1 string, arg2 string) {
	__taint_sink(arg1)
}

func __taint_sink(o interface{}) {}
