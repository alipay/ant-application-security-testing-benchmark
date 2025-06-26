
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 二阶闭包
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/closure_function_004_T/closure_function_004_T
// evaluation information end

package closure_function_004_T

func closure_function_004_T(__taint_src interface{}) {
	outer := func() func() {
		return func() {
			__taint_sink(__taint_src)
		}
	}

	closure_function_004_Function := outer()
	closure_function_004_Function()
}

func __taint_sink(o interface{}) {}
