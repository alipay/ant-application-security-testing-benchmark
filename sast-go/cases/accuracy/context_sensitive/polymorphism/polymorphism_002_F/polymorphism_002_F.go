
// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->多态
// scene introduction = 继承
// level = 2 
// bind_url = accuracy/context_sensitive/polymorphism/polymorphism_002_F/polymorphism_002_F
// evaluation information end

package polymorphism_002_F

func polymorphism_002_F(__taint_src interface{}) {
	var sub Base
	sub = Sub2{}
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
