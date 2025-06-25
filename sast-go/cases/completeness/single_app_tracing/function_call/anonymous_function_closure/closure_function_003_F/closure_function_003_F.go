
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 二阶闭包
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/closure_function_003_F/closure_function_003_F
// evaluation information end

package closure_function_003_F

func closure_function_003_F(__taint_src interface{}) {
	a := __taint_src
	a = "safe"
	outer := func() func() {
		return func() {
			__taint_sink(a)
		}
	}

	closure_function_003_Function := outer()
	closure_function_003_Function()
}

func __taint_sink(o interface{}) {}
