
// evaluation information start
// real case = false
// evaluation item = 准确度->上下文敏感分析->参数/返回值传递
// scene introduction = 参数值传递->引用传递->this
// level = 2
// bind_url = accuracy/context_sensitive/argument_return_value_passing/argument_passing_reference_008_F/argument_passing_reference_008_F
// evaluation information end

package main
import "os/exec"

type Person struct {
	Name string
}

func argument_passing_reference_008_F(__taint_src string) {
	person := Person{Name: __taint_src}

	(&person).UpdateNamePointer("safe")
	__taint_sink((&person).GetNamePointer())
}

func (p *Person) UpdateNamePointer(newName string) {
	p.Name = newName
}

func (p *Person) GetNamePointer() string {
	return p.Name
}
func __taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", o.(string)).Run()
	}

func main() {
    __taint_src := "taint_src_value"
    argument_passing_reference_008_F(__taint_src)
}