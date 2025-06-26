
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型->指针
// scene introduction = 指针-new
// level = 2
// bind_url = completeness/single_app_tracing/datatype/pointer/pointer_new_002_F/pointer_new_002_F
// evaluation information end

package pointer_new_002_F

func pointer_new_002_F(__taint_src string) {
	var ps *string
	ps = new(string)
	*ps = __taint_src
	*ps = "_"
	__taint_sink(*ps)
}

func __taint_sink(o interface{}) {
}
