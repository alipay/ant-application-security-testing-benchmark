
// evaluation information start
// real case = true
// evaluation item = 准确度->对象敏感与域敏感分析->区分不同结构体的不同字段
// scene introduction = 域敏感-路径长度
// level = 3
// bind_url = accuracy/field_sensitive/struct/field_len_003_T/field_len_003_T
// evaluation information end

package field_len_003_T

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
type G struct {
	f F
}
type H struct {
	g G
}
type I struct {
	h H
}
type J struct {
	i I
}
type K struct {
	j J
}
type L struct {
	k K
}
type M struct {
	l L
}
type N struct {
	m M
}
type O struct {
	n N
}
type P struct {
	o O
}
type Q struct {
	p P
}

func field_len_003_T(__taint_src string) {
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
	var g G
	g.f = f
	var h H
	h.g = g
	var i I
	i.h = h
	var j J
	j.i = i
	var k K
	k.j = j
	var l L
	l.k = k
	var m M
	m.l = l
	var n N
	n.m = m
	var o O
	o.n = n
	var p P
	p.o = o
	var q Q
	q.p = p
	__taint_sink(q.p.o.n.m.l.k.j.i.h.g.f.e.d.c.b.a.data)
}

func __taint_sink(o interface{}) {
}
