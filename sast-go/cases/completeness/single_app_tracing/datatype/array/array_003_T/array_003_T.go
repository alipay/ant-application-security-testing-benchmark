
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 二维
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_003_T/array_003_T
// evaluation information end

package array_003_T

func array_003_T(__taint_src string) {
	var str = [3][1]string{[1]string{__taint_src}, [1]string{"b"}, [1]string{"c"}}
	__taint_sink(str)
}

func __taint_sink(o interface{}) {
}
