
// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 参数值传递->引用传递->map
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_reference_002_T/argument_passing_reference_002_T
// evaluation information end

package argument_passing_reference_002_T

func argument_passing_reference_002_T(__taint_src interface{}) {
	obj := map[string]interface{}{
		"data": "_",
	}
	process(obj, __taint_src)
	__taint_sink(obj["data"])
}

func process(obj map[string]interface{}, src interface{}) {
	obj["data"] = src
}

func __taint_sink(o interface{}) {
}
