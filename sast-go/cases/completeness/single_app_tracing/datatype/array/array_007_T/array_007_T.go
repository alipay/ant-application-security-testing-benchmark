
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->数组
// scene introduction = 
// level = 2
// bind_url = completeness/single_app_tracing/datatype/array/array_007_T/array_007_T
// evaluation information end

package array_007_T

func array_007_T(__taint_src string) {
	var str = [...]string{__taint_src, "b", "c"}
	__taint_sink(str)
}

func __taint_sink(o interface{}) {}
