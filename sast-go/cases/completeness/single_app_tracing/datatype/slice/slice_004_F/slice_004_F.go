
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->切片
// scene introduction = append操作
// level = 2
// bind_url = completeness/single_app_tracing/datatype/slice/slice_004_F/slice_004_F
// evaluation information end

package slice_004_F

func slice_004_F(__taint_src string) {
	var s []string = []string{"b", "c"}
	s = append(s, "_")
	__taint_sink(s)
}

func __taint_sink(o interface{}) {
}
