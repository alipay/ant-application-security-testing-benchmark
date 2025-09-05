
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->字符串
// scene introduction = 字符串类型
// level = 2
// bind_url = completeness/single_app_tracing/datatype/string/string_002_F/string_002_F
// evaluation information end

package string_002_F

func string_002_F(__taint_src string) {
	var sani string = __taint_src
	sani = "_"
	__taint_sink(sani)
}

func __taint_sink(o interface{}) {
}
