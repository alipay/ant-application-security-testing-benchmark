// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_value_012_T/argument_passing_value_012_T
// evaluation information end

package argument_passing_value_012_T

func argument_passing_value_012_T(__taint_src string) {
	process(__taint_src)
}

func process(arg string) {
	__taint_sink(arg)
}

func __taint_sink(o interface{}) {
}
