
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = 整型
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/primitives_int_002_F/primitives_int_002_F
// evaluation information end

package primitives_int_002_F

func primitives_int_002_F(__taint_src int) {
	var sani int = __taint_src
	sani = 0
	__taint_sink(sani)
}

func __taint_sink(o interface{}) {
}
