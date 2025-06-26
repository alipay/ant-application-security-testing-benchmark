
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
// scene introduction = 匿名函数作参数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_007_F/higher_order_function_007_F
// evaluation information end

package higher_order_function_007_F

func higher_order_function_007_F(__taint_src string) {
	result := higher_order_function_007_Function(func(a string, b string) string {
		return "safe" + b
	}, __taint_src)
	__taint_sink(result)
}

func higher_order_function_007_Function(callback func(a string, b string) string, src string) string {
	return callback(src, "_")
}

func __taint_sink(o interface{}) {
}
