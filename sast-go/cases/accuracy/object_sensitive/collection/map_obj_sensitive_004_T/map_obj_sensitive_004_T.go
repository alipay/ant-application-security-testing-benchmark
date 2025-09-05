
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = map->映射
// level = 2
// bind_url = accuracy/object_sensitive/collection/map_obj_sensitive_004_T/map_obj_sensitive_004_T
// evaluation information end

package map_obj_sensitive_004_T

func map_obj_sensitive_004_T(__taint_src string) {
	m := map[string]string{
		"key1": __taint_src,
	}
	n := map[string]string{
		"key1": "_",
	}
	_ = n
	__taint_sink(m)
}

func __taint_sink(o interface{}) {
}
