
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = 嵌套函数
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_value_008_T/argument_passing_value_008_T
// evaluation information end

package argument_passing_value_008_T

func argument_passing_value_008_T(__taint_src string) {
	outer := func(input string) {
		inner := func(innerInput interface{}) {
			__taint_sink(innerInput)
		}

		inner(input)
	}

	outer(__taint_src)
}

func __taint_sink(o interface{}) {}
