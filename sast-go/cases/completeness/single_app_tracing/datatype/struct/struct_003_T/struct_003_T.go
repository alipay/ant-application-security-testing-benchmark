
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->结构体
// scene introduction = 结构体
// level = 2
// bind_url = completeness/single_app_tracing/datatype/struct/struct_003_T/struct_003_T
// evaluation information end

package struct_003_T

type A struct {
	data string
}

func struct_003_T(__taint_src string) {
	p := new(A)
	p.data = __taint_src
	__taint_sink(p)
}

func __taint_sink(o interface{}) {
}
