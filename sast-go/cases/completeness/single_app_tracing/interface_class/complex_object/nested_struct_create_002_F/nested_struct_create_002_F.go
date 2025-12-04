// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
// scene introduction = 嵌套结构体创建
// level = 2
// date = 2025-11-17 14:30:00
// bind_url = completeness/single_app_tracing/interface_class/complex_object/nested_struct_create_002_F/nested_struct_create_002_F
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
	address Address
}

type Company struct {
	name    string
	manager Person
}

func nested_struct_create_002_F(__taint_src string) {
	// 场景特点：创建多层嵌套结构体但使用安全值初始化
	comp := Company{
		name: "TechCorp",
		manager: Person{
			name: "safe_value",
			address: Address{
				city:   "Beijing",
				street: "Main St",
			},
		},
	}
	taint_sink(comp.manager.name)
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	nested_struct_create_002_F(__taint_src)
}
