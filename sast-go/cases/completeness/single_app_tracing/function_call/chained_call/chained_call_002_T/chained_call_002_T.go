
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->链式调用
// scene introduction = 链式调用
// level = 2
// bind_url = completeness/single_app_tracing/function_call/chained_call/chained_call_002_T/chained_call_002_T
// evaluation information end

package chained_call_002_T

func chained_call_002_T(__taint_src string) {
	new(A).setName("_").clearName().setName(__taint_src).process()
}

type A struct {
	name string
}

func (a *A) setName(name string) *A {
	a.name = name
	return a
}

func (a *A) clearName() *A {
	a.name = ""
	return a
}

func (a *A) process() {
	__taint_sink(a.name)
}

func __taint_sink(o interface{}) {}
