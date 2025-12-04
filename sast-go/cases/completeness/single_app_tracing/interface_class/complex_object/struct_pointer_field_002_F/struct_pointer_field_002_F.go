// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
// scene introduction = 结构体指针字段访问
// level = 2
// date = 2025-11-17 14:31:00
// bind_url = completeness/single_app_tracing/interface_class/complex_object/struct_pointer_field_002_F/struct_pointer_field_002_F
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

type Address struct {
	city   string
	street string
}

type Person struct {
	name    string
	address *Address
}

func struct_pointer_field_002_F(__taint_src string) {
	// 场景特点：通过指针访问嵌套结构体字段但使用安全值
	addr := &Address{
		city:   "safe_value",
		street: "Main St",
	}
	person := Person{
		name:    "John",
		address: addr,
	}
	taint_sink(person.address.city)
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	struct_pointer_field_002_F(__taint_src)
}
