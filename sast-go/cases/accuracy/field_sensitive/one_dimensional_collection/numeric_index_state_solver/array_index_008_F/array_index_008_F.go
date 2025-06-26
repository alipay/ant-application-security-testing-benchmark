
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分一维字典/列表/数组的不同元素->索引值非数字的场景，能够区分不同索引上特定元素的状态（需要求解）
// scene introduction = 数组->数组索引->需求解
// level = 4
// bind_url = accuracy/field_sensitive/one_dimensional_collection/numeric_index_state_solver/array_index_008_F/array_index_008_F
// evaluation information end

package array_index_008_F

func array_index_008_F(__taint_src string) {
	var str = [4]string{"b", __taint_src, "c", "d"}
	__taint_sink(str[1+2])
}

func __taint_sink(o interface{}) {
}
