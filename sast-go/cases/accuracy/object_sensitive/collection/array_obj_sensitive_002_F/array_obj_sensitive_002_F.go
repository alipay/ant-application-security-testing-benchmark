
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = 数组->复合数据类型
// level = 2
// bind_url = accuracy/object_sensitive/collection/array_obj_sensitive_002_F/array_obj_sensitive_002_F
// evaluation information end

package array_obj_sensitive_002_F

func array_obj_sensitive_002_F(__taint_src string) {
	var str = [3]string{__taint_src, "b", "c"}
	var str2 = [3]string{"a", "b", "c"}
	_ = str
	__taint_sink(str2)
}

func __taint_sink(o interface{}) {
}
