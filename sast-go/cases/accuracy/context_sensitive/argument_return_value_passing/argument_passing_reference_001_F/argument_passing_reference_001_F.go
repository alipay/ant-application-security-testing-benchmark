
// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 参数值传递->引用传递->map
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_reference_001_F/argument_passing_reference_001_F
// evaluation information end

package argument_passing_reference_001_F

func argument_passing_reference_001_F(__taint_src interface{}) {
	obj := map[string]interface{}{
		"data": __taint_src,
	}
	process(obj)
	__taint_sink(obj["data"])
}

func process(obj map[string]interface{}) {
	obj["data"] = "_"
}

func __taint_sink(o interface{}) {
}
