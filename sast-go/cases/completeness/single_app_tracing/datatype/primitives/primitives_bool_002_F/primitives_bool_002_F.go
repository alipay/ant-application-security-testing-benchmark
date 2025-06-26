
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->基础数据类型
// scene introduction = 布尔类型
// level = 2
// bind_url = completeness/single_app_tracing/datatype/primitives/primitives_bool_002_F/primitives_bool_002_F
// evaluation information end

package primitives_bool_002_F

func primitives_bool_002_F(__taint_src bool) {
	sani := __taint_src
	sani = false
	__taint_sink(sani)
}

func __taint_sink(o interface{}) {
}
