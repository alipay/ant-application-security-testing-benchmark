
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分字典/列表/数组的不同元素
// scene introduction = 切片->二维切片
// level = 2
// bind_url = accuracy/object_sensitive/collection/slice_obj_sensitive_004_F/slice_obj_sensitive_004_F
// evaluation information end

package slice_obj_sensitive_004_F

func slice_obj_sensitive_004_F(__taint_src string) {
	s := [][]string{
		[]string{"a"},
		[]string{"b", "c"},
		[]string{"d", "e", __taint_src},
	}
	s2 := [][]string{
		[]string{"a"},
		[]string{"b", "c"},
		[]string{"d", "e", "f"},
	}
	_ = s
	__taint_sink(s2)
}

func __taint_sink(o interface{}) {
}
