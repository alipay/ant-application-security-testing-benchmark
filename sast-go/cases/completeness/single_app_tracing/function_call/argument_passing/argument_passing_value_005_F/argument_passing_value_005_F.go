
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 参数顺序
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_value_005_F/argument_passing_value_005_F
// evaluation information end

package argument_passing_value_005_F

func argument_passing_value_005_F(__taint_src string) {
	process(__taint_src, "_")
}

func process(arg1 string, arg2 string) {
	__taint_sink(arg2)
}

func __taint_sink(o interface{}) {}
