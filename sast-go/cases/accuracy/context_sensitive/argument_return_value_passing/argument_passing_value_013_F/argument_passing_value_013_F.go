// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 参数值传递->多函数
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_value_013_F/argument_passing_value_013_F
// evaluation information end

package argument_passing_value_013_F

func argument_passing_value_013_F(__taint_src interface{}) {
	var clean string = "_"
	process1(clean)
	process2(__taint_src)
}

func process1(arg interface{}) {
	__taint_sink(arg)
}

func process2(arg interface{}) {
}

func __taint_sink(o interface{}) {
}
