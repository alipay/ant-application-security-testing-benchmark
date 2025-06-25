
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值为数字的场景，能够区分不同索引上特定元素的状态(无需求解)
// scene introduction = map->字典/映射（Map）-域敏感
// level = 3
// bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_no_solver/map_field_sensitive_003_F/map_field_sensitive_003_F
// evaluation information end

package map_field_sensitive_003_F

func map_field_sensitive_003_F(__taint_src string) {
	m := make(map[string]string, 2)
	m[__taint_src] = "value1"
	m["key2"] = "value2"
	__taint_sink(m[__taint_src])
}

func __taint_sink(o interface{}) {
}
