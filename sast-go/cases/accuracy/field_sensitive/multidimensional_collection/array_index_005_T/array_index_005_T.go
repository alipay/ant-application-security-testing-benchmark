
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分多维字典/列表/数组的不同元素
// scene introduction = 数组索引->二维
// level = 4
// bind_url = accuracy/field_sensitive/multidimensional_collection/array_index_005_T/array_index_005_T
// evaluation information end

package array_index_005_T

func array_index_005_T(__taint_src string) {
	var str = [3][1]string{[1]string{__taint_src}, [1]string{"b"}, [1]string{"c"}}
	__taint_sink(str[0][0])
}

func __taint_sink(o interface{}) {
}
