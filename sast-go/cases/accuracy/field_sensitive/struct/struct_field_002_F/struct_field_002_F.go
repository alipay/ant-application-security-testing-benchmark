
// evaluation information start
// real case = false
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同结构体的不同字段
// scene introduction = 域敏感
// level = 3
// bind_url = accuracy/field_sensitive/struct/struct_field_002_F/struct_field_002_F
// evaluation information end

package struct_field_002_F

type A struct {
	data string
	sani string
}

func struct_field_002_F(__taint_src string) {
	p := A{
		data: __taint_src,
		sani: "_",
	}
	__taint_sink(p.sani)
}

func __taint_sink(o interface{}) {
}
