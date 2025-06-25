// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 返回值传递->具名返回值
// level = 2 
// bind_url = accuracy/context_sensitive/argument_return_value_passing/named_return_002_T/named_return_002_T
// evaluation information end

package named_return_002_T

func named_return_002_T(__taint_src interface{}) {
	a := "_"

	ret := processData(__taint_src, a)
	__taint_sink(ret)
}

func processData(s interface{}, i interface{}) (ret interface{}) {
	ret = s
	return
}

func __taint_sink(o interface{}) {}
