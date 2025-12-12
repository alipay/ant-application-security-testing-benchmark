// evaluation information start
// real case = false
// evaluation item = 完整度->单应用跟踪完整度->接口与类->复杂对象
// scene introduction = 多层嵌套字段读取
// level = 2+
// date = 2025-11-17 14:32:00
// bind_url = completeness/single_app_tracing/interface_class/complex_object/deep_nested_field_read_002_F/deep_nested_field_read_002_F
// evaluation information end

package main

import (
	"fmt"
	"os/exec"
)

type Street struct {
	name string
	no   int
}

type Address struct {
	city   string
	street Street
}

type Person struct {
	name    string
	address Address
}

type Company struct {
	name    string
	manager Person
}

func deep_nested_field_read_002_F(__taint_src string) {
	// 场景特点：读取四层嵌套结构体的最深层字段但使用安全值
	comp := Company{
		name: "TechCorp",
		manager: Person{
			name: "John",
			address: Address{
				city: "Beijing",
				street: Street{
					name: "safe_value",
					no:   100,
				},
			},
		},
	}
	taint_sink(comp.manager.address.street.name)
}

func taint_sink(o interface{}) {
	_ = exec.Command("sh", "-c", fmt.Sprintf("%v", o)).Run()
}

func main() {
	__taint_src := "taint_src_value"
	deep_nested_field_read_002_F(__taint_src)
}
