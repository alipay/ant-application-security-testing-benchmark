
// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->多态
// scene introduction = 接口指针
// level = 2 
// bind_url = accuracy/context_sensitive/polymorphism/polymorphism_004_F/polymorphism_004_F
// evaluation information end
package main
import "os/exec"

func polymorphism_004_F(__taint_src string) {
	var student Person = &Student{Name: "Alice", Age: 20, GPA: 3.8}
	var teacher Person = &Teacher{Name: __taint_src, Age: 35, major: "Math"}
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
    polymorphism_004_F(__taint_src)
}