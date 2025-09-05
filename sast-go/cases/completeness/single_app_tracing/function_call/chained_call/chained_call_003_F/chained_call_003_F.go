
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->链式调用
// scene introduction = 链式调用
// level = 2
// bind_url = completeness/single_app_tracing/function_call/chained_call/chained_call_003_F/chained_call_003_F
// evaluation information end

package chained_call_003_F

func chained_call_003_F(__taint_src string) {
	NewB().SetName(__taint_src).ClearName().SetOther().Process()
}

type B struct {
	name string
}

func NewB() *B {
	return &B{name: ""}
}

func (b *B) SetName(name string) *B {
	b.name = name
	return b
}

func (b *B) ClearName() *B {
	b.name = "_"
	return b
}

func (b *B) SetOther() *B {
	b.name = "foo"
	return b
}

func (b *B) Process() {
	__taint_sink(b.name)
}

func __taint_sink(o interface{}) {
}
