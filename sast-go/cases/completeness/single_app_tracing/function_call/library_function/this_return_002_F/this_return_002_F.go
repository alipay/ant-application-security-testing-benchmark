package this_return_002_F


// evaluation information start
// real case = false
// evaluation item =完整度->单应用跟踪完整度->函数和方法调用->库函数调用
// scene introduction = this传播到返回值
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/library_function/this_return_002_F/this_return_002_F
// evaluation information end

func this_return_002_F(__taint_src string) {
	taintedData := "_"
	result := taintToString(taintedData)
	__taint_sink(result)
}

func taintToString(s interface{}) string {
	b := s.(string)
	return b
}

func __taint_sink(o interface{}) {
}
