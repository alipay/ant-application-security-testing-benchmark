
// evaluation information start
// real case = true
// evaluation item = 准确度->上下文敏感分析->多态
// scene introduction = 继承
// level = 2
// bind_url = accuracy/context_sensitive/polymorphism/polymorphism_001_T/polymorphism_001_T
// evaluation information end

package main
import "os/exec"

func polymorphism_001_T(__taint_src interface{}) {
	var sub Base
	sub = Sub1{}
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
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    polymorphism_001_T(__taint_src)
}