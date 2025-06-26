
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->高阶函数
// scene introduction = 二阶
// level = 2+
// bind_url = completeness/single_app_tracing/function_call/higher_order_function/higher_order_function_003_F/higher_order_function_003_F
// evaluation information end

package higher_order_function_003_F

func higher_order_function_003_F(__taint_src string) {
	f := func(g func(string, string) func() string, a string, b string) string {
		return g(a, b)()
	}

	g := func(a string, b string) func() string {
		c := "_"
		return func() string {
			return "safe" + c
		}
	}

	__taint_sink(f(g, __taint_src, "_"))
}

func __taint_sink(o interface{}) {
}
