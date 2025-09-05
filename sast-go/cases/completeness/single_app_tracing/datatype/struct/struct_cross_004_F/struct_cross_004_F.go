
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->结构体
// scene introduction = 结构体-内嵌
// level = 2+
// bind_url = completeness/single_app_tracing/datatype/struct/struct_cross_004_F/struct_cross_004_F
// evaluation information end

package struct_cross_004_F

type A struct {
	data string
}
type B struct {
	A
	s string
}

func struct_cross_004_F(__taint_src string) {
	p := B{A{
		data: "__",
	}, "_"}
	__taint_sink(p)
}

func __taint_sink(o interface{}) {
}
