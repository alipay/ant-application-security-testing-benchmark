
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->表达式->语言特有的表达式
// scene introduction = 空白标识符
// level = 2
// bind_url = completeness/single_app_tracing/expression/special_expression/blank_identifier_002_F/blank_identifier_002_F
// evaluation information end

package blank_identifier_002_F

func blank_identifier_002_F(__taint_src string) {
	_, a := getData(__taint_src)
	__taint_sink(a)
}

func getData(__taint_src string) (string, string) {
	return __taint_src, ""
}

func __taint_sink(o interface{}) {
}
