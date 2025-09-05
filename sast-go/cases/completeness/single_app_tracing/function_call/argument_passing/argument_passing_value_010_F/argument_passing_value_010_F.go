
// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->函数和方法调用->参数传递
// scene introduction = this
// level = 2
// bind_url = completeness/single_app_tracing/function_call/argument_passing/argument_passing_value_010_F/argument_passing_value_010_F
// evaluation information end

package argument_passing_value_010_F

type Person struct {
	Name string
}

func argument_passing_value_010_F(__taint_src string) {
	person := Person{Name: "safe"}

	person.UpdateNamePointer(__taint_src)
	__taint_sink(person.GetNamePointer())
}

func (p Person) UpdateNamePointer(newName string) {
	p.Name = newName
}

func (p Person) GetNamePointer() string {
	return p.Name
}

func __taint_sink(o interface{}) {
}
