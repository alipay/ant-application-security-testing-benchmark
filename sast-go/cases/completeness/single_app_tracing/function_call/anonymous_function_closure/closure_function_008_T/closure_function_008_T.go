
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 三阶闭包
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/closure_function_008_T/closure_function_008_T
// evaluation information end

package closure_function_008_T

func closure_function_008_T(__taint_src interface{}) {
	outer := func() func() func() {
		outerVar := __taint_src

		middle := func() func() {
			middleVar := outerVar

			inner := func() {
				__taint_sink(middleVar)
			}

			return inner
		}

		return middle
	}

	closure_function_008_TunctionM := outer()
	closure_function_008_Tunction := closure_function_008_TunctionM()
	closure_function_008_Tunction()
}

func __taint_sink(o interface{}) {
}
