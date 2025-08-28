
// evaluation information start
// real case = true
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = this
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_value_009_T/argument_passing_value_009_T
// evaluation information end

package main
import "os/exec"

type Person struct {
	Name string
}

func argument_passing_value_009_T(__taint_src string) {
	person := Person{Name: __taint_src}

	person.UpdateNamePointer("safe")
	__taint_sink(person.GetNamePointer())
}

func (p Person) UpdateNamePointer(newName string) {
	p.Name = newName
}

func (p Person) GetNamePointer() string {
	return p.Name
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    argument_passing_value_009_T(__taint_src)
}