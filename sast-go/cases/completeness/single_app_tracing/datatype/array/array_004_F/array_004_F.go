
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 二维
// level = 2
// bind_url = completeness/object_tracing/datatype/array_slice/array_004_F/array_004_F
// evaluation information end

package array_004_F

func array_004_F(__taint_src string) {
	var str = [3][1]string{[1]string{__taint_src}, [1]string{"b"}, [1]string{"c"}}
	var s = [3][1]string{[1]string{"a"}, [1]string{"b"}, [1]string{"c"}}
	_ = str
	__taint_sink(s)
}

func __taint_sink(o interface{}) {
}
