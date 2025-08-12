
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->方法重写
// scene introduction = 多态->接口
// level = 2
// bind_url = completeness/single_app_tracing/function_call/override/polymorphism_override_004_F/polymorphism_override_004_F
// evaluation information end
package main
import "os/exec"

func polymorphism_override_004_F(__taint_src string) {
	var student Person = &Student{Name: "Alice", Age: 20, GPA: 3.8}
	var teacher Person = &Teacher{Name: "Bob", Age: 35, major: "Math"}
	_ = teacher
	__taint_sink(student.Run())
}

type Person interface {
	Run() string
}

type Student struct {
	Name string
	Age  int
	GPA  float64
}

func (s *Student) Run() string {
	return s.Name
}

type Teacher struct {
	Name  string
	Age   int
	major string
}

func (t *Teacher) Run() string {
	return t.Name
}

func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    polymorphism_override_004_F(__taint_src)
}