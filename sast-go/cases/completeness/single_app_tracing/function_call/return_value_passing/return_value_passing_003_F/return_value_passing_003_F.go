
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->返回值传递
// scene introduction = 返回值传递
// level = 2
// bind_url = completeness/single_app_tracing/function_call/return_value_passing/return_value_passing_003_F/return_value_passing_003_F
// evaluation information end

package return_value_passing_003_F

func return_value_passing_003_F(__taint_src string) {
	data := process(__taint_src)
	__taint_sink(data)
}

func process(__taint_src string) string {
	tmp := __taint_src
	tmp = "_"
	return tmp
}

func __taint_sink(o interface{}) {}
