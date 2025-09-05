
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->匿名函数/闭包
// scene introduction = 三阶闭包
// level = 2
// bind_url = completeness/single_app_tracing/function_call/anonymous_function_closure/closure_function_007_F/closure_function_007_F
// evaluation information end

package closure_function_007_F

func closure_function_007_F(__taint_src interface{}) {
	outer := func() func() func() {
		data := "safe"
		outerVar := data

		middle := func() func() {
			middleVar := outerVar

			inner := func() {
				__taint_sink(middleVar)
			}

			return inner
		}

		return middle
	}

	closure_function_007_FunctionM := outer()
	closure_function_007_Function := closure_function_007_FunctionM()
	closure_function_007_Function()
}

func __taint_sink(o interface{}) {
}
