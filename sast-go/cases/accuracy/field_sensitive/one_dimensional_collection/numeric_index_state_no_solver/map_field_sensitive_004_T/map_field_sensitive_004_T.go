
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
// scene introduction = map->delete函数
// level = 3
// bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/map_field_sensitive_004_T/map_field_sensitive_004_T
// evaluation information end

package map_field_sensitive_004_T

func map_field_sensitive_004_T(__taint_src string) {
	m := make(map[string]string, 2)
	m["key1"] = __taint_src
	m["key2"] = "value"
	m["key2"] = __taint_src
	delete(m, "key1")
	__taint_sink(m["key2"])
}

func __taint_sink(o interface{}) {
}
