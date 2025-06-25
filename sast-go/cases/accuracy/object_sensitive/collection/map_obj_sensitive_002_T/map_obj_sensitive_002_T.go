
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = map->对象敏感
// level = 2
// bind_url = accuracy/object_sensitive/collection/map_obj_sensitive_002_T/map_obj_sensitive_002_T
// evaluation information end

package map_obj_sensitive_002_T

func map_obj_sensitive_002_T(__taint_src string) {
	m := make(map[string]string, 1)
	m[__taint_src] = "key1"
	__taint_sink(m)
}

func __taint_sink(o interface{}) {
}
