
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->指针
// scene introduction = 指针类型
// level = 2
// bind_url = completeness/single_app_tracing/datatype/pointer/pointer_001_T/pointer_001_T
// evaluation information end

package pointer_001_T

func pointer_001_T(__taint_src string) {
	ps := &__taint_src
	__taint_sink(*ps)
}

func __taint_sink(o interface{}) {
}
