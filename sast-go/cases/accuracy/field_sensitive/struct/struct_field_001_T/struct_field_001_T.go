
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同结构体的不同字段
// scene introduction = 域敏感
// level = 3
// bind_url = accuracy/field_sensitive/struct/struct_field_001_T/struct_field_001_T
// evaluation information end

package struct_field_001_T

type A struct {
	data string
	sani string
}

func struct_field_001_T(__taint_src string) {
	p := A{
		data: __taint_src,
		sani: "_",
	}
	__taint_sink(p.data)
}

func __taint_sink(o interface{}) {
}
