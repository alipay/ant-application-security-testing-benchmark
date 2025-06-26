package arg_return_002_F


// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->库函数调用
// scene introduction = 从参数传播到返回值
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/library_function/arg_return_002_F/arg_return_002_F
// evaluation information end

func arg_return_002_F(__taint_src string) {
	taintedData := __taint_src
	var sList []string
	result := appendTaint(sList, taintedData)
	__taint_sink(result)
}

func appendTaint(newTaint []string, taintSrc string) []string {
	taintSrc = "_"
	b := append(newTaint, taintSrc)
	return b
}

func __taint_sink(o interface{}) {
}
