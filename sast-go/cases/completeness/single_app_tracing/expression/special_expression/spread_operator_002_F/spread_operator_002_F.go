
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 扩展运算符
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/spread_operator_002_F/spread_operator_002_F
// evaluation information end

package spread_operator_002_F

func spread_operator_002_F(__taint_src string) {
	array1 := []string{"a", "b", __taint_src}
	array := append([]string{"c"}, array1...)
	_ = array
	__taint_sink("aa")
}

func __taint_sink(o interface{}) {
}
