// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 参数值传递->参数顺序
// level = 2 
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_value_015_F/argument_passing_value_015_F
// evaluation information end

package argument_passing_value_015_F

func argument_passing_value_015_F(__taint_src string) {
	process(__taint_src, "_")
}

func process(arg1 string, arg2 string) {
	__taint_sink(arg2)
}

func __taint_sink(o interface{}) {}
