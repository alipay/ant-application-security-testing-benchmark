
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = 数组->复合数据类型
// level = 2
// bind_url = accuracy/object_sensitive/collection/array_obj_sensitive_004_F/array_obj_sensitive_004_F
// evaluation information end

package array_obj_sensitive_004_F

func array_obj_sensitive_004_F(__taint_src string) {
	var str = [3][1]string{[1]string{__taint_src}, [1]string{"b"}, [1]string{"c"}}
	var str2 = [3][1]string{[1]string{"a"}, [1]string{"b"}, [1]string{"c"}}
	_ = str
	__taint_sink(str2)
}

func __taint_sink(o interface{}) {
}
