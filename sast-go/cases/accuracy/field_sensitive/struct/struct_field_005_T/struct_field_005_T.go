
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同结构体的不同字段
// scene introduction = 域敏感-匿名域
// level = 3
// bind_url = accuracy/field_sensitive/struct/struct_field_005_T/struct_field_005_T
// evaluation information end

package struct_field_005_T

type A struct {
	string
}

func struct_field_005_T(__taint_src string) {
	var p1 A
	p1.string = __taint_src
	__taint_sink(p1.string)
}

func __taint_sink(o interface{}) {
}
