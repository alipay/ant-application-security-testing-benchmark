
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->数据类型和结构->结构体
// scene introduction = 跨结构体访问变量
// level = 2
// bind_url = completeness/single_app_tracing/datatype/struct/struct_cross_001_T/struct_cross_001_T
// evaluation information end

package struct_cross_001_T

type A struct {
	data string
}
type B struct {
	data string
}

func struct_cross_001_T(__taint_src string) {
	pa := A{
		data: __taint_src,
	}
	var pb B
	pb.data = pa.data
	__taint_sink(pb)
}

func __taint_sink(o interface{}) {
}
