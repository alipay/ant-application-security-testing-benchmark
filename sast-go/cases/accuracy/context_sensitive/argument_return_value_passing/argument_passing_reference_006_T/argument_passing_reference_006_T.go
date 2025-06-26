
// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 参数值传递->引用传递->map
// level = 2
// bind_url = cases/accuracy/context_sensitive/argument_return_value_passing/argument_passing_reference_006_T/argument_passing_reference_006_T
// evaluation information end

package argument_passing_reference_006_T

func argument_passing_reference_006_T(__taint_src interface{}) {
	objA := map[string]interface{}{"name": __taint_src}
	objB := map[string]interface{}{"name": "Bob"}

	swap(objA, objB)
	__taint_sink(objB["name"])
}

func swap(obj1, obj2 map[string]interface{}) {
	temp := obj1["name"]
	obj1["name"] = obj2["name"]
	obj2["name"] = temp
}

func __taint_sink(o interface{}) {
}
