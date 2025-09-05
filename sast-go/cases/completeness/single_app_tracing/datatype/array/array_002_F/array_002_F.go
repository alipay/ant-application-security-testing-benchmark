
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_002_F/array_002_F
// evaluation information end

package array_002_F

func array_002_F(__taint_src string) {
	var str = [3]string{__taint_src, "b", "c"}
	var s = [3]string{"a", "b", "c"}
	_ = str
	__taint_sink(s)
}

func __taint_sink(o interface{}) {}
