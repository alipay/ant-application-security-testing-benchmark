
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->切片
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/datatype/slice/slice_002_F/slice_002_F
// evaluation information end

package slice_002_F

func slice_002_F(__taint_src string) {
	var ss []string = []string{__taint_src, "b", "c"}
	var s []string = []string{"a", "b", "c"}
	_ = ss
	__taint_sink(s)
}

func __taint_sink(o interface{}) {
}
