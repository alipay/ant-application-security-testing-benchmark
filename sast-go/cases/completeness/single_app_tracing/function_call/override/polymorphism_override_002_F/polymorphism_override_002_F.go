// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
// scene introduction = 多态
// level = 2
// bind_url = completeness/single_app_tracing/function_call/override/polymorphism_override_002_F/polymorphism_override_002_F
// evaluation information end

package polymorphism_override_002_F

func polymorphism_override_002_F(__taint_src interface{}) {
	var sub Base
	sub = Sub2{} // 将Sub1实例化
	__taint_sink(sub.call(__taint_src))
}

type Base interface {
	call(src interface{}) interface{}
}

type Sub1 struct{}

func (s Sub1) call(src interface{}) interface{} {
	return src
}

type Sub2 struct{}

func (s Sub2) call(src interface{}) interface{} {
	return "_"
}

func __taint_sink(o interface{}) {
}
