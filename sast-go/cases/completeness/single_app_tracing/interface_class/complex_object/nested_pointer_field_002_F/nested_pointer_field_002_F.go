// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
// scene introduction = 结构体嵌套指针字段
// level = 2
// date = 2025-11-17 14:37:00
// bind_url = completeness/single_app_tracing/interface_class/complex_object/nested_pointer_field_002_F/nested_pointer_field_002_F
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

type Contact struct {
	phone *string
	email string
}

type Person struct {
	name    string
	contact Contact
}

func nested_pointer_field_002_F(__taint_src string) {
	// 场景特点：访问嵌套结构体中的指针字段但使用安全值
	phone := "safe_value"
	contact := Contact{
		phone: &phone,
		email: "test@example.com",
	}
	person := Person{
		name:    "John",
		contact: contact,
	}
	taint_sink(*person.contact.phone)
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	nested_pointer_field_002_F(__taint_src)
}
