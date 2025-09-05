
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同结构体的不同字段
// scene introduction = 域敏感-路径长度
// level = 3
// bind_url = accuracy/field_sensitive/struct/field_len_005_T/field_len_005_T
// evaluation information end

package field_len_005_T

type A struct {
	data string
}
type B struct {
	a A
}
type C struct {
	b B
}
type D struct {
	c C
}
type E struct {
	d D
}
type F struct {
	e E
}

func field_len_005_T(__taint_src string) {
	pa := A{
		data: __taint_src,
	}
	var b B
	b.a = pa
	var c C
	c.b = b
	var d D
	d.c = c
	var e E
	e.d = d
	var f F
	f.e = e
	p := f.e.d.c
	q := p.b.a.data
	__taint_sink(q)
}

func __taint_sink(o interface{}) {
}
